package com.whoiszxl.base.exception;

import com.whoiszxl.base.entity.Result;

public class ExceptionCast {

    public static void cast(Result result){
        throw new CustomException(result);
    }

    public static void castValidateEx(Result result){
        throw new ValidateException(result);
    }

    public static void castNormalEx(Result result) {
        throw new NormalException(result);
    }

}
