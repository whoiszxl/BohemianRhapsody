package com.whoiszxl.user.pojo;

import com.whoiszxl.base.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @description: 用户实体
 * @author: whoiszxl
 **/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="zxl_user")
public class ZxlUser extends BasePojo {

    private static final long serialVersionUID = 1L;


    /**
     * 主键Id
     */
    @Id
    private String id;

    /**
     * 用户名
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
