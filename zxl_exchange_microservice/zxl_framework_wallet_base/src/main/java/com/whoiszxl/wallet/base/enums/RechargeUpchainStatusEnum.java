package com.whoiszxl.wallet.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 充值上链状态枚举
 * @author: whoiszxl
 * @create: 2019-08-15
 **/
@Getter
@AllArgsConstructor
public enum RechargeUpchainStatusEnum {

    STATUS_FAIL("失败", 0),
    STATUS_SUCCESS("成功", 1),
    STATUS_UN_CONFIRM("未确认的", 2);
    private String msg;
    private Integer code;

}
