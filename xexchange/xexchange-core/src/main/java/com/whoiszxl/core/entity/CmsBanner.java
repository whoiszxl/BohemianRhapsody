package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 首页轮播表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "cms_banner")
public class CmsBanner {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 轮播图名称
     */
    private String name;

    /**
     * 轮播位置：0->PC首页轮播；1->app首页轮播
     */
    private Boolean type;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 上下线状态：0->下线；1->上线
     */
    private Integer status;

    /**
     * 点击数
     */
    private Integer clickCount;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
