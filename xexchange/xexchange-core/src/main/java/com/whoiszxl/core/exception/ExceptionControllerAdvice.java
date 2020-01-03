package com.whoiszxl.core.exception;

import com.whoiszxl.core.entity.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常捕获
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 拦截非法参数异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result myErrorHandler(IllegalArgumentException e) {
        log.info("拦截参数异常={}",e);
        return Result.buildError(e.getMessage());
    }


    /**
     * 拦截异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result myErrorHandler(Exception ex) {
        log.info("拦截异常={}",ex);
        return Result.buildError("未知错误");
    }

    /**
     * 错误请求方式异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.info("错误请求方式异常={}",ex);
        StringBuilder methods = new StringBuilder();
        //支持的请求方式
        String[] supportedMethods = ex.getSupportedMethods();
        assert supportedMethods != null;
        for (String method : supportedMethods) {
            methods.append(method);
        }
        return Result.buildError("Request method " + ex.getMethod() + "  not supported ! supported method : " + methods + "!");
    }
}
