package com.whoiszxl.xwallet.core.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@Data
public class Account {
    private String account;
    private String address;
    //私钥路径
    private String walletFile;
    private BigDecimal balance = BigDecimal.ZERO;
    //地址燃料余额，对Token,USDT有用
    private BigDecimal gas = BigDecimal.ZERO;
}
