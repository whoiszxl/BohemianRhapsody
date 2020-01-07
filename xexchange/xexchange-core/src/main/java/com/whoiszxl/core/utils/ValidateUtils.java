package com.whoiszxl.core.utils;

import com.whoiszxl.core.entity.base.Result;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 校验参数有效性工具类
 * @author: whoiszxl
 * @create: 2020-01-07
 **/
public class ValidateUtils {


    /**
     * 校验密码是否一致及强度
     * @param password
     * @param rePassword
     */
    public static void checkPasswordEqual(String password, String rePassword) {
        if(!StringUtils.equals(password, rePassword)) {
            ExceptionCast.castValidateEx(Result.buildError("rePassword not equal"));
        }
    }

    public static void checkPasswordLevel(String password) {
        CheckPasswordUtils.LEVEL passwordLevel = CheckPasswordUtils.getPasswordLevel(password);
        if(passwordLevel.equals(CheckPasswordUtils.LEVEL.EASY)) {
            ExceptionCast.castValidateEx(Result.buildError("password too simple"));
        }
    }

    /**
     *  校验手机格式
     * @param phone
     */
    public static void checkPhoneRegex(String phone) {
        if(!RegexUtils.checkPhone(phone)) {
            ExceptionCast.castValidateEx(Result.buildError("phone invaild"));
        }
    }

}
