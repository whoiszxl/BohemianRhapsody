package com.whoiszxl.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: zuul web过滤器
 * @author: whoiszxl
 * @create: 2019-08-13
 **/
@Component
public class WebFilter extends ZuulFilter {
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
        return true; //是否执行该过滤器
    }

    @Override
    public Object run() throws ZuulException {
        //传递header令牌
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(authorization)) {
            requestContext.addZuulRequestHeader("Authorization", authorization);
        }
        return null;
    }
}
