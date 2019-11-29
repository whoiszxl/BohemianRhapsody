package com.whoiszxl.core.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 交易输入
 * @author: whoiszxl
 * @create: 2019-11-16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TXOutput {

    /**
     * 数值金额
     */
    private Integer value;

    /**
     * 锁定脚本
     */
    private String scriptPubKey;

    /**
     * 判断解锁数据是否能够解锁交易输出
     *
     * @param unlockingData
     * @return
     */
    public boolean canBeUnlockedWith(String unlockingData) {
        return this.getScriptPubKey().endsWith(unlockingData);
    }
}
