package com.whoiszxl.base.exception;

import com.whoiszxl.base.entity.Result;

/**
 * @description: 自定义用户异常，不需要记录日志，所以独立一个出来
 * @author: whoiszxl
 * @create: 2019-08-08 11:46
 **/
public class NormalException extends RuntimeException {

    //错误代码
    Result result;

    public NormalException(Result result){
        this.result = result;
    }
    public Result getResultCode(){
        return result;
    }
}
