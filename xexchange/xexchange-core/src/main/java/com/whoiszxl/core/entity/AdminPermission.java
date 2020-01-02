package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 管理员权限表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "admin_permission")
public class AdminPermission {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 权限名称
     */
    private String perName;

    /**
     * 权限值
     */
    private String perValue;

    /**
     * 父权限ID
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
