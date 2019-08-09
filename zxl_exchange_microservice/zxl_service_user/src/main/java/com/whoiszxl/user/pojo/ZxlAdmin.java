package com.whoiszxl.user.pojo;

import com.whoiszxl.base.pojo.BasePojo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @description: 管理员实体
 * @author: whoiszxl
 **/
@Data
@Accessors(chain = true)
public class ZxlAdmin extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private String id;


    /**
     * 管理员用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

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

    /**
     * 谷歌验证码
     */
    private String googleCode;

    /**
     * 状态(0：无效 1：有效)
     */
    private Integer status;

    /**
     * 最后登录
     */
    private LocalDateTime lastLogin;

}
