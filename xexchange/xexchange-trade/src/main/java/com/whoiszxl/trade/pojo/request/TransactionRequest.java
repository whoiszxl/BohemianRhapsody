package com.whoiszxl.trade.pojo.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 挂单Vo实体
 * @author: whoiszxl
 * @create: 2019-10-31
 **/
@Data
public class TransactionRequest {

    private Long memberId;

    private BigDecimal price;

    private BigDecimal count;

    private Long contractId;

    private Long coinId;

    private Long replaceCoinId;

    //1 买入   -1 卖出
    private Integer type;

}
