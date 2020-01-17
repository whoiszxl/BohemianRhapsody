package com.whoiszxl.usercenter.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: coin vo类
 * @author: whoiszxl
 * @create: 2020-01-15
 **/
@Data
public class CoinVo {

    private Long id;

    /**
     * 货币名称
     */
    private String coinName;

    /**
     * 英文标识
     */
    private String coinMark;

    /**
     * 货币logo
     */
    private String coinLogo;

    /**
     * 货币类型： mainnet：主网币 token：代币
     */
    private String coinType;

    /**
     * 币种描述
     */
    private String coinContent;

    /**
     * 币总数量
     */
    private BigDecimal coinTotalNum;

    /**
     * 币种小数位
     */
    private Integer coinDecimalsNum;

    /**
     * 买入手续费
     */
    private BigDecimal coinBuyFee;

    /**
     * 卖出手续费
     */
    private BigDecimal coinSellFee;

    /**
     * 该币种的链接地址
     */
    private String coinUrl;

    /**
     * 提币手续费
     */
    private BigDecimal feeWithdraw;

    /**
     * 充值确认数
     */
    private Integer confirms;

    /**
     * 展示顺序
     */
    private Integer sort;

    /**
     * 币种状态，0：关闭 1：开启
     */
    private Integer status;

}
