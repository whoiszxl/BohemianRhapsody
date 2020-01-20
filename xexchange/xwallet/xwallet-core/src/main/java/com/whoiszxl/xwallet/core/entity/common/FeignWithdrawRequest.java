package com.whoiszxl.xwallet.core.entity.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: feign远程提现请求体
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
@Data
public class FeignWithdrawRequest {

    /** 提现到什么地址 */
    private String toAddress;

    /** 提现金额 */
    private BigDecimal amount;

    /** 备注 */
    private String remark;

    /** 提现ID */
    private Long withdrawId;
}
