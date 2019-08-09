package com.whoiszxl.base.exception;

import com.google.common.collect.ImmutableMap;
import com.whoiszxl.base.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 统一捕获异常信息
 * @author: whoiszxl
 * @create: 2019-08-08 11:50
 **/
@ControllerAdvice
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>, Result> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>,Result> builder = ImmutableMap.builder();

    //捕获CustomException此类异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customException(CustomException customException){
        customException.printStackTrace();
        //记录日志
        LOGGER.error("catch exception:{}",customException.getMessage());
        Result resultCode = customException.getResultCode();
        return resultCode;
    }

    //捕获CustomException此类异常
    @ExceptionHandler(ValidateException.class)
    @ResponseBody
    public Result validateException(ValidateException validateException){
        Result resultCode = validateException.getResultCode();
        return resultCode;
    }



    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception exception){
        exception.printStackTrace();
        //记录日志
        LOGGER.error("catch exception:{}",exception.getMessage());
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应异常
        Result result = EXCEPTIONS.get(exception.getClass());
        if(result !=null){
            return result;
        }else{
            //返回报错
            return Result.buildError();
        }


    }

    static {
        //定义异常类型所对应的错误代码
        builder.put(HttpMessageNotReadableException.class, Result.buildError());
    }

}
