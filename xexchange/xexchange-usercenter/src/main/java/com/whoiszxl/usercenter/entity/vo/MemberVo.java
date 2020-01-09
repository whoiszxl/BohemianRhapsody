package com.whoiszxl.usercenter.entity.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 用户Vo
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
@Data
public class MemberVo {

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
     * 最后登录
     */
    private Date lastLogin;

    /**
     * 创建时间
     */
    private Date createdAt;
}
