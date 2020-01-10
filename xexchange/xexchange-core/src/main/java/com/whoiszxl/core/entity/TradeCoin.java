package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 币种表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "trade_coin")
public class TradeCoin {

    @Id
    @Excel(name = "币种ID", orderNum = "1", width = 10)
    private Long id;
    /**
     * 货币名称
     */
    @Excel(name = "货币名称", orderNum = "1", width = 20)
    @NotBlank(message = "货币名称不能为空")
    private String coinName;

    /**
     * 英文标识
     */
    @Excel(name = "货币英文标识", orderNum = "1", width = 20)
    @NotBlank(message = "货币英文标识不能为空")
    private String coinMark;

    /**
     * 货币logo
     */
    private String coinLogo;

    /**
     * 货币类型： mainnet：主网币 token：代币
     */
    @Excel(name = "货币类型", orderNum = "1", width = 20)
    private String coinType;

    /**
     * 币种描述
     */
    @Excel(name = "币种描述", orderNum = "1", width = 40)
    private String coinContent;

    /**
     * 币总数量
     */
    @Excel(name = "币总数量", orderNum = "1", width = 20)
    private BigDecimal coinTotalNum;

    /**
     * 币种小数位
     */
    @Excel(name = "币种小数位", orderNum = "1", width = 20)
    private Integer coinDecimalsNum;

    /**
     * 买入手续费
     */
    @Excel(name = "买入手续费", orderNum = "1", width = 20)
    private BigDecimal coinBuyFee;

    /**
     * 卖出手续费
     */
    @Excel(name = "卖出手续费", orderNum = "1", width = 20)
    private BigDecimal coinSellFee;

    /**
     * 该币种的链接地址
     */
    @Excel(name = "币种链接地址", orderNum = "1", width = 40)
    private String coinUrl;

    /**
     * 智能合约abi接口
     */
    private String contractAbi;

    /**
     * 智能合约地址
     */
    private String contractAddress;

    /**
     * rpc路径
     */
    private String rpcUrl;

    /**
     * rpc用户名
     */
    private String rpcUsername;

    /**
     * rpc密码
     */
    private String rpcPassword;

    /**
     * 最大提币额
     */
    @Excel(name = "最大提币额", orderNum = "1", width = 20)
    private BigDecimal maxWithdraw;

    /**
     * 最小提币额
     */
    @Excel(name = "最小提币额", orderNum = "1", width = 20)
    private BigDecimal minWithdraw;

    /**
     * 提币手续费
     */
    @Excel(name = "提币手续费", orderNum = "1", width = 20)
    private BigDecimal feeWithdraw;

    /**
     * 钱包储存路径
     */
    private String walletUrl;

    /**
     * 钱包密钥
     */
    private String walletKey;

    /**
     * 充值确认数
     */
    @Excel(name = "充值确认数", orderNum = "1", width = 20)
    private Integer confirms;

    /**
     * 展示顺序
     */
    @Excel(name = "展示顺序", orderNum = "1", width = 20)
    private Integer sort;

    /**
     * 币种状态，0：关闭 1：开启
     */
    @Excel(name = "币种状态", orderNum = "1", width = 20)
    private Boolean status;

    /**
     * 创建时间
     */
    @Excel(name = "记录创建时间", orderNum = "1", width = 25)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @Excel(name = "记录更新时间", orderNum = "1", width = 25)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;


}
