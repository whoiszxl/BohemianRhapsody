package com.whoiszxl.base.exception;

import com.whoiszxl.base.entity.Result;

/**
 * @description: 自定义异常
 * @author: whoiszxl
 * @create: 2019-08-08 11:46
 **/
public class CustomException extends RuntimeException {

    //错误代码
    Result result;

    public CustomException(Result result){
        this.result = result;
    }
    public Result getResultCode(){
        return result;
    }
}
