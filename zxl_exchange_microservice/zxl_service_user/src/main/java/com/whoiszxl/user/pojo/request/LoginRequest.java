package com.whoiszxl.user.pojo.request;

import lombok.Data;

/**
 * @description: 登录请求类
 * @author: whoiszxl
 * @create: 2019-08-09
 **/
@Data
public class LoginRequest {

    private String username;
    private String password;
}
