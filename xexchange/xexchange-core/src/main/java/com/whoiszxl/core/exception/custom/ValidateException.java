package com.whoiszxl.core.exception.custom;

import com.whoiszxl.core.entity.base.Result;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-07
 **/
public class ValidateException extends RuntimeException {

    //错误代码
    Result result;

    public ValidateException(Result result){
        this.result = result;
    }
    public Result getResult(){
        return result;
    }
}
