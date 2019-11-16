package com.whoiszxl.pow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description: POW工作量计算结果
 * @author: whoiszxl
 * @create: 2019-11-15
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PowResult {

    /**
     * 计数器
     */
    private long nonce;

    /**
     * 计算区块的hash值
     */
    private String hash;
}
