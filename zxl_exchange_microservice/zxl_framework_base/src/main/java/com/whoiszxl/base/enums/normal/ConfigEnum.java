package com.whoiszxl.base.enums.normal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 配置枚举
 * @author: whoiszxl
 * @create: 2019-09-26
 **/
@Getter
@AllArgsConstructor
public enum ConfigEnum {

    //主页下的八个分类图标
    INDEX_TEN_NAVIGATOR("INDEX_TEN_NAVIGATOR"),
    INDEX_AD_BANNER("INDEX_AD_BANNER");

    private String key;
}
