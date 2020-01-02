package com.whoiszxl.core.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 管理员访问日志表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "admin_access_log")
public class AdminAccessLog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 访问IP
     */
    private String accessIp;

    /**
     * 访问的方法
     */
    private String accessMethod;

    /**
     * 访问的模块
     */
    private Integer accessModule;

    /**
     * 访问的时间
     */
    private Date accessTime;

    /**
     * 访问的管理员ID
     */
    private Long adminId;

    /**
     * 操作
     */
    private String operation;

    /**
     * 访问路径
     */
    private String uri;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
