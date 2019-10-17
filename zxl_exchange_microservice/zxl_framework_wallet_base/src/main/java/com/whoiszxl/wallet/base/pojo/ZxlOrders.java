package com.whoiszxl.wallet.base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单成交表
 * </p>
 *
 * @author whoiszxl
 * @since 2019-10-17
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "zxl_currency")
public class ZxlOrders {

    private static final long serialVersionUID = 1L;

    /**
     * 买家用户ID
     */
    private String userId;

    /**
     * 挂单ID
     */
    private String transactionId;

    /**
     * 交易对第一个币种ID
     */
    private Integer currencyId;

    /**
     * 交易对第二个币种ID
     */
    private Integer replaceCurrencyId;

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
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
