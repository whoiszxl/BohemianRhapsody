package com.whoiszxl.core.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "cms_app_version")
public class CmsAppVersion {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    /**
     * 下载地址
     */
    private String downloadUrl;

    /**
     * 平台，类型字符串 IOS && ANDROID
     */
    private Integer platform;

    /**
     * 版本号
     */
    private String appVersion;

    /**
     * 更新标题
     */
    private String appTitle;

    /**
     * 更新内容
     */
    private String appContent;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
