package com.whoiszxl.wallet.base.pojo;

import com.whoiszxl.base.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 * 用户余额表
 * </p>
 *
 * @author whoiszxl
 * @since 2019-09-05
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "zxl_user_balance")
public class ZxlUserBalance extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id",strategy = "increment")
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 币种ID
     */
    private Integer currencyId;

    /**
     * 数量
     */
    private BigDecimal allBalance;

    /**
     * 冻结数量
     */
    private BigDecimal lockBalance;

    /**
     * 冻结数量
     */
    private BigDecimal usableBalance;

    /**
     * 充值地址状态，0：关闭 1：开启
     */
    private Integer status;

}
