package com.whoiszxl.common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.whoiszxl.base.pojo.BasePojo;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("zxl_banner")
public class ZxlBanner extends BasePojo {


    private static final long serialVersionUID = 1L;

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


}