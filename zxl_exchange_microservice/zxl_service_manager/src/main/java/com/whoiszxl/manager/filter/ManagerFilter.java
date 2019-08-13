package com.whoiszxl.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.whoiszxl.base.enums.role.UserRoleEnum;
import com.whoiszxl.base.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: zuul manager过滤器
 * @author: whoiszxl
 * @create: 2019-08-13
 **/
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String filterType() {
        return "pre"; //前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0; //优先级，越大越低
    }

    @Override
    public boolean shouldFilter() {
        return true; //过滤器开关
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if(request.getMethod().equals("OPTIONS")) {
            return null;
        }
        String url = request.getRequestURL().toString();
        if(url.indexOf("/admin/login") > 0) {
            return null;
        }
        String authHeader = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtils.parseJWT(token);
            if(claims != null) {
                if(UserRoleEnum.ROLE_ADMIN.getRoleName().equals(claims.get("roles"))) {
                    requestContext.addZuulRequestHeader("Authorization", authHeader);
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false); //中止运行
        requestContext.setResponseStatusCode(401);//http状态码
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
