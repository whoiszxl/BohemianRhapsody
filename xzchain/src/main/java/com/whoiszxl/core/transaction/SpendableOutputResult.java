package com.whoiszxl.core.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description: 可花费交易输出
 * @author: whoiszxl
 * @create: 2019-11-26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpendableOutputResult {


    /**
     * 交易时的支付金额，这个金额大于或等于实际转账的金额
     * 比如我有三个output,分别为2,4,5，然后需要转出去10个，就需要把2,4,5全部转出去，再找零1个
     */
    private Integer accumulated;

    /**
     * 未花费的交易
     * Key为未花费交易的txHash，Value为使用到的output下标索引
     */
    private Map<String, Integer[]> unspentOuts;
}
