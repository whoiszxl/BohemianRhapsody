package com.whoiszxl.core.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 交易类
 * @author: whoiszxl
 * @create: 2019-11-13
 **/
@Data
@ToString
@AllArgsConstructor
public class Transaction {

    /**
     * 交易Hash
     */
    private byte[] txId;

    private TXInput[] inputs;

    private TXOutput[] outputs;
}
