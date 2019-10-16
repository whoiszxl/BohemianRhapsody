package com.whoiszxl.wallet.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 币种名称和ID对应枚举
 * @author: whoiszxl
 * @create: 2019-08-15
 **/
@Getter
@AllArgsConstructor
public enum CurrencyNameIdEnum {

    BTC("BTC", 1);

    private String currencyName;
    private Integer currencyId;

}
