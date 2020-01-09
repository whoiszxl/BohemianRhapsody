package com.whoiszxl.core.exception;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.exception.custom.JwtAuthException;
import com.whoiszxl.core.exception.custom.ValidateException;

/**
 * @description: 异常捕获
 * @author: whoiszxl
 * @create: 2020-01-07
 **/
public class ExceptionCatcher {

    public static void catchJwtAuthEx(){
        throw new JwtAuthException();
    }

    public static void catchValidateEx(Result result){
        throw new ValidateException(result);
    }

}
