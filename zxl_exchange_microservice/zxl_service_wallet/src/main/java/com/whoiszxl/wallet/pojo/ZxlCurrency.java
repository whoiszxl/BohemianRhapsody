package com.whoiszxl.wallet.pojo;

import com.whoiszxl.base.pojo.BasePojo;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 币种表
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-15
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "zxl_currency")
public class ZxlCurrency extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 货币名称
     */
    @Id
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
    private Float currencyBuyFee;

    /**
     * 卖出手续费
     */
    private Float currencySellFee;

    /**
     * 该币种的链接地址
     */
    private String currencyUrl;

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
    private Integer maxWithdraw;

    /**
     * 最小提币额
     */
    private Integer minWithdraw;

    /**
     * 钱包储存路径
     */
    private String walletUrl;

    /**
     * 钱包密钥
     */
    private String walletKey;

    /**
     * 展示顺序
     */
    private Integer sort;

    /**
     * 币种状态，0：关闭 1：开启
     */
    private Integer status;


}