package com.whoiszxl.user.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description: 用户vo实体
 * @author: whoiszxl
 * @create: 2019-10-14
 **/
@Data
public class ZxlUserVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 国家码
     */
    private String countryCode;


}
