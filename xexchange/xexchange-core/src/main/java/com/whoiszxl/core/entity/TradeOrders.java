package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单成交表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "trade_orders")
public class TradeOrders {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 买家用户ID
     */
    private String memberId;

    /**
     * 挂单ID
     */
    private String transactionId;

    /**
     * 交易对第一个币种ID
     */
    private Integer coinId;

    /**
     * 交易对第二个币种ID
     */
    private Integer replaceCoinId;

    /**
     * 成交价格
     */
    private BigDecimal price;

    /**
     * 委托总数量
     */
    private BigDecimal successCount;

    /**
     * 0：买入 1：卖出
     */
    private Integer type;

    /**
     * 创建时间,成交时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
