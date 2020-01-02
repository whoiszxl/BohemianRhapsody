package com.whoiszxl.core.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Entity
@Data
@Table(name = "admin")
@Accessors(chain = true)
public class Admin {


    @Excel(name = "主键ID", orderNum = "1", width = 25D) //配置Excel导出项，序号从1开始，列宽25D
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 用户名
     */
    @Excel(name = "用户名", orderNum = "1", width = 25)
    @NotBlank(message = "用户名不能为空")
    @Column(nullable = false,unique = true)
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名", orderNum = "1", width = 25)
    @NotBlank(message = "真实姓名不能为空")
    private String realname;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "1", width = 40)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 手机
     */
    @Excel(name = "手机", orderNum = "1", width = 25)
    @NotBlank(message = "手机号码不允许为空")
    private String phone;

    /**
     * 国家码
     */
    @Excel(name = "国家码", orderNum = "1", width = 25)
    @NotBlank(message = "国家码不允许为空")
    private String countryCode;

    /**
     * 谷歌验证码
     */
    @Excel(name = "谷歌验证码", orderNum = "1", width = 25)
    private String googleCode;

    /**
     * 最后登录的IP
     */
    @Excel(name = "最后登录的IP", orderNum = "1", width = 25)
    private String lastLoginIp;

    /**
     * 最后登录
     */
    @Excel(name = "用户最后登录时间", orderNum = "1", width = 25)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLogin;

    /**
     * 关联的角色ID
     */
    @NotNull(message = "角色不能为空")
    private Long roleId;

    /**
     * 状态(0：无效 1：有效)
     */
    @Excel(name = "管理员状态", orderNum = "1", width = 25)
    private Integer status;

    /**
     * 创建时间
     */
    @Excel(name = "记录创建时间", orderNum = "1", width = 25)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @Excel(name = "记录更新时间", orderNum = "1", width = 25)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;


}
