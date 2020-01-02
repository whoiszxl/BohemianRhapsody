package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "member")
public class Member {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

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
    private String googleKey;

    /**
     * 谷歌验证码是否开启，默认不开启
     */
    private Integer googleStatus;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String district;

    /**
     * 用户等级
     */
    private String gradeLevel;

    /**
     * 当前等级每日提现额度
     */
    private Integer dayWithdrawCount;

    /**
     * 当前等级交易手续费率
     */
    private BigDecimal tradeFeeRate;

    /**
     * 用户登录次数
     */
    private Integer loginCount;

    /**
     * 状态(0：无效 1：有效)
     */
    private Integer status;

    /**
     * 最后登录
     */
    private Date lastLogin;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
