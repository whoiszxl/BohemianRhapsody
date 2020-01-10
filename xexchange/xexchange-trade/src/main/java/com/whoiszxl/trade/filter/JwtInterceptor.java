package com.whoiszxl.trade.filter;

import com.whoiszxl.core.enums.role.MemberRoleEnum;
import com.whoiszxl.core.exception.ExceptionCatcher;
import com.whoiszxl.core.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: jwt拦截器
 * @author: whoiszxl
 * @create: 2019-08-09
 **/
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;


    /**
     * 对header中的token进行解析，始终放行，拦截器仅做解析验证，逻辑判断在controller下再操作。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("jwt 拦截器运行了");
        final String header = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(header) && header.startsWith("Bearer ")) {
            final String token = header.substring(7);

            //对令牌进行验证
            try {
                Claims claims = jwtUtils.parseJWT(token);
                String roles = (String) claims.get("roles");
                if(StringUtils.equals(roles, MemberRoleEnum.ROLE_ADMIN.getRoleName())){
                    request.setAttribute(MemberRoleEnum.ROLE_ADMIN.getRoleAttrKey(), claims);
                }
                if(StringUtils.equals(roles, MemberRoleEnum.ROLE_USER.getRoleName())){
                    request.setAttribute(MemberRoleEnum.ROLE_USER.getRoleAttrKey(), claims);
                }
            }catch (Exception e){
                ExceptionCatcher.catchJwtAuthEx();
            }
        }

        return true;
    }
}
