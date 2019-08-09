package com.whoiszxl.user.pojo.request;

import lombok.Data;

/**
 * @description: 注册请求类
 * @author: whoiszxl
 * @create: 2019-08-09
 **/
@Data
public class RegisterRequest {

    private String mobile;

    private String code;

    private String password;

    private String rePassword;
}
