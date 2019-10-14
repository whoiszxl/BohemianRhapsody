package com.whoiszxl.base.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户错误码枚举
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode {

    JWT_TOKEN_AUTH_FAIL("jwt令牌鉴权失败", 10001);

    private String msg;
    private Integer code;
}
