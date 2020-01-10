package com.whoiszxl.core.enums.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 币种名称和ID对应枚举
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
@Getter
@AllArgsConstructor
public enum CoinNameIdEnum {

    BTC("BTC", 1);

    private String currencyName;
    private Integer currencyId;
}
