package com.whoiszxl.base.exception;

import com.whoiszxl.base.entity.Result;

public class ExceptionCast {

    public static void cast(Result result){
        throw new CustomException(result);
    }
}
