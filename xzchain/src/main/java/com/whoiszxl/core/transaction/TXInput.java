package com.whoiszxl.core.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 交易输出
 * @author: whoiszxl
 * @create: 2019-11-16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TXInput {

    /**
     * 被引用交易的hash值
     */
    private byte[] txId;

    /**
     * 交易输出索引
     */
    private int txOutputIndex;

    /**
     * 解锁脚本
     */
    private String scriptSig;


    /**
     * 判断解锁数据是否能够解锁交易输出
     * @param unlockingData
     * @return
     */
    public boolean canUnlockOutputWith(String unlockingData) {
        return this.getScriptSig().endsWith(unlockingData);
    }

}
