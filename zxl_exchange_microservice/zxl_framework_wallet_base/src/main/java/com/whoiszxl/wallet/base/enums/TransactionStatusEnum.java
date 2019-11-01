package com.whoiszxl.wallet.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 挂单状态枚举
 * @author: whoiszxl
 * @create: 2019-11-01
 **/
@Getter
@AllArgsConstructor
public enum TransactionStatusEnum {

    TRADE_OPEN("代表部分交易，可交易", 0),
    TRADE_CLOSE("所有已成交，交易结束", 1),
    TRADE_CANCEL("用户撤单", -1)
    ;

    private String message;
    private Integer value;

}
