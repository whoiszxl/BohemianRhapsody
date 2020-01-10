package com.whoiszxl.trade.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * @description: currency vo类
 * @author: whoiszxl
 * @create: 2019-09-07
 **/
@Data
public class CoinVo {

    private static final long serialVersionUID = 1L;

    /**
     * 货币名称
     */
    private String currencyName;

    /**
     * 英文标识
     */
    private String currencyMark;

    /**
     * 货币logo
     */
    private String currencyLogo;

    /**
     * 货币类型： mainnet：主网币 token：代币
     */
    private String currencyType;

    /**
     * 币种描述
     */
    private String currencyContent;

    /**
     * 币总数量
     */
    private BigDecimal currencyTotalNum;

    /**
     * 币种小数位
     */
    private Integer currencyDecimalsNum;

    /**
     * 买入手续费
     */
    private BigDecimal currencyBuyFee;

    /**
     * 卖出手续费
     */
    private BigDecimal currencySellFee;

    /**
     * 该币种的链接地址
     */
    private String currencyUrl;

    /**
     * 最大提币额
     */
    private BigDecimal maxWithdraw;

    /**
     * 最小提币额
     */
    private BigDecimal minWithdraw;

    /**
     * 提币手续费
     */
    private BigDecimal feeWithdraw;

    /**
     * 展示顺序
     */
    private Integer sort;

    /**
     * 币种状态，0：关闭 1：开启
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;

}
