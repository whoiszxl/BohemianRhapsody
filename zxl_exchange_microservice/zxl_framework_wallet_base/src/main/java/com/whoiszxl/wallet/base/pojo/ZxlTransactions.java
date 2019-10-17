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
 * 挂单表
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
public class ZxlTransactions {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 交易对第一个币种ID
     */
    private Integer currencyId;

    /**
     * 交易对第二个币种ID
     */
    private Integer replaceCurrencyId;

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
     * 0：买入 1：卖出
     */
    private Integer type;

    /**
     * 0代表部分交易，可交易，1是所有已成交，交易结束， -1用户撤单
     */
    private Integer status;

    /**
     * 创建时间,挂单时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
