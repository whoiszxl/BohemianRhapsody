package com.whoiszxl.core.exception.custom;

import com.whoiszxl.core.entity.base.Result;

/**
 * @description: 自定义jwt鉴权异常
 * @author: whoiszxl
 * @create: 2020-01-07
 **/
public class JwtAuthException extends RuntimeException {


    //错误代码
    Result result;

    public JwtAuthException(Result result){
        this.result = result;
    }
    public Result getResult(){
        return result;
    }

}
