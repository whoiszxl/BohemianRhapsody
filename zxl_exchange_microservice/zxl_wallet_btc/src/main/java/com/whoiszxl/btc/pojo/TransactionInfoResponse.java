package com.whoiszxl.btc.pojo;

import lombok.Data;

import java.util.List;

/**
 * @description: 交易详情返回类
 * @author: whoiszxl
 * @create: 2019-09-02
 **/
@Data
public class TransactionInfoResponse {

    /** 交易金额，正数表示该交易增加钱包余额，负数表示该交易减少钱包余额 */
    private int amount;

    /** 交易确认数，0表示未确认，-1表示存在冲突 */
    private int confirmations;

    /** 币基交易则该值为true */
    private boolean generated;

    /** 交易所在区块的哈希 */
    private String blockhash;

    /** 交易所在区块的编号 */
    private int blockindex;

    /** 交易所在区块的unix时间 */
    private long blocktime;

    /** 交易ID */
    private String txid;

    /** 交易打包时间戳 */
    private long time;

    /** 节点收到交易的unix时间 */
    private long timereceived;

    /** 输入输出详情数组 */
    private List<Details> details;

    /** 串行序列化字符串 */
    private String hex;

    /**
     * 输入输出详情实体类
     */
    @Data
    public class Details {

        /** 对端地址 */
        private String address;

        /**
         * 交易类别,可以是：
         * send：发送交易
         * receive：接收交易
         * immature：未成熟币基交易
         * generate：成熟币基交易
         * orphan：孤儿块中的币基交易
         */
        private String category;

        /** 金额 */
        private int amount;

        /** 输出序号 */
        private int vout;
    }
}
