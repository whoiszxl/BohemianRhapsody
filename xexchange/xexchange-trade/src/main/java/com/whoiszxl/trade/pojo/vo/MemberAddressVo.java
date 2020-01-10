package com.whoiszxl.trade.pojo.vo;

import lombok.Data;

/**
 * @description: 用户地址返回Vo类
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@Data
public class MemberAddressVo {

    /**
     * 币种ID
     */
    private Long coinId;

    /**
     * 充值地址
     */
    private String rechargeAddress;

    /**
     * 钱包状态，0：关闭 1：开启
     */
    private Integer status;


}

