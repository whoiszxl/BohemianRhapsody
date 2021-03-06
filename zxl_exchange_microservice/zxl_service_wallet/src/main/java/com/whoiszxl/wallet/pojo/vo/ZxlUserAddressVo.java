package com.whoiszxl.wallet.pojo.vo;

import lombok.Data;

/**
 * @description: 用户地址返回Vo类
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@Data
public class ZxlUserAddressVo{

    /**
     * 币种ID
     */
    private Integer currencyId;

    /**
     * 充值地址
     */
    private String rechargeAddress;

    /**
     * 钱包状态，0：关闭 1：开启
     */
    private Integer status;


}

