package com.whoiszxl.wallet.pojo.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 挂单Vo实体
 * @author: whoiszxl
 * @create: 2019-10-31
 **/
@Data
public class TransactionRequest {

    private String userId;

    private BigDecimal price;

    private BigDecimal count;

    private Integer contractId;

    private Integer currencyId;

    private Integer replaceCurrencyId;

    //1 买入   -1 卖出
    private Integer type;

}
