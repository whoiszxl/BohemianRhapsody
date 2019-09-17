package com.whoiszxl.btc.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 交易返回值
 * @author: whoiszxl
 * @create: 2019-09-02
 **/
@Data
public class ListTransactionResponse {

    /** 地址 */
    private String address;

    /** 交易类别 */
    private String category;

    /** 金额 */
    private BigDecimal amount;

    /** 输出序号 */
    private int vout;

    /** 确认数量 */
    private int confirmations;

    /** 是否币基交易 */
    private boolean generated;

    /** 区块哈希 */
    private String blockhash;

    /** 区块序号 */
    private int blockindex;

    /** 区块时间戳 */
    private long blocktime;

    /** 交易id */
    private String txid;

    /** 交易打包时间戳 */
    private long time;

    /** 收到交易的时间戳 */
    private long timereceived;
}
