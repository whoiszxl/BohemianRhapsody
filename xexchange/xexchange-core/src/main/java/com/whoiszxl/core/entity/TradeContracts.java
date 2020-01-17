package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易对表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "trade_contracts")
public class TradeContracts {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 交易对名称
     */
    private String contractName;

    /**
     * 交易对类型：1：主版   2：P版  3:创业版
     */
    private Integer contractType;

    /**
     * 交易对第一个币种ID
     */
    private Integer coinId;

    /**
     * 交易对第二个币种ID
     */
    private Integer replaceCoinId;

    /**
     * 购买者所出费率
     */
    private BigDecimal buyerFee;

    /**
     * 出售者所出费率
     */
    private BigDecimal sellerFee;

    /**
     * 最大挂单笔数
     */
    private Integer maxOrders;

    /**
     * 展示顺序
     */
    private Integer sort;

    /**
     * 交易对状态，0：关闭 1：开启
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
