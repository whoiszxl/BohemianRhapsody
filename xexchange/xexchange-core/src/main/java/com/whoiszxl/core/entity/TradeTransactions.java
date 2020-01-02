package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 挂单表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "trade_transactions")
public class TradeTransactions {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 用户ID
     */
    private String memberId;

    /**
     * 交易对第一个币种ID
     */
    private Integer coinId;

    /**
     * 交易对第二个币种ID
     */
    private Integer replaceCoinId;

    /**
     * 委托价格
     */
    private BigDecimal price;

    /**
     * 委托总数量
     */
    private BigDecimal totalCount;

    /**
     * 当前可交易数量（挂单的金额可能超过当前所有挂单的总和）
     */
    private BigDecimal currentCount;

    /**
     * 1：买入 -1：卖出
     */
    private Integer type;

    /**
     * 0代表部分交易，可交易，1是所有已成交，交易结束， -1用户撤单
     */
    private Integer status;

    /**
     * 创建时间,挂单时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
