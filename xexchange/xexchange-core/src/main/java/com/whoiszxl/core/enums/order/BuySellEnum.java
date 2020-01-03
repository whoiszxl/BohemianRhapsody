package com.whoiszxl.core.enums.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 买卖枚举
 * @author: whoiszxl
 * @create: 2019-10-31
 **/
@Getter
@AllArgsConstructor
public enum BuySellEnum {

    //主页下的八个分类图标
    BUY("BUY", 1),
    SELL("SELL", -1);

    private String key;
    private Integer value;
}
