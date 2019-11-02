package com.whoiszxl.wallet.base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 交易对表
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
@Table(name = "zxl_contracts")
public class ZxlContracts {

    private static final long serialVersionUID = 1L;

    /**
     * 交易对ID
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id",strategy = "increment")
    private Integer id;
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
    private Integer currencyId;

    /**
     * 交易对第二个币种ID
     */
    private Integer replaceCurrencyId;

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
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
