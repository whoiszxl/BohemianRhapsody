package com.whoiszxl.core.constant;

/**
 * @description: Redis键名规范
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
public class RedisKeyNameConstant {

    /** 保存交易对的最新价队列,末尾拼上交易对名 **/
    public static final String WALLET_CONTRACT_LATESTPRICE = "wallet:contract:latestprice:";


    /** 钱包服务与机器地址端口对应 HASH **/
    public static final String WALLET_IPADDR = "wallet:ipaddr";
}

