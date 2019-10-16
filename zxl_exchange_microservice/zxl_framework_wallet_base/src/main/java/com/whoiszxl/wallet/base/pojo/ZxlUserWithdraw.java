package com.whoiszxl.wallet.base.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whoiszxl.base.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户提现记录表
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
@Table(name = "zxl_user_withdraw")
public class ZxlUserWithdraw extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id
    private String userId;

    /**
     * 币种ID
     */
    private Integer currencyId;

    /**
     * 币种名称
     */
    private String currencyName;

    /**
     * 提现交易hash
     */
    private String txHash;

    /**
     * 总提现额
     */
    private BigDecimal withdrawAll;

    /**
     * 提现手续费
     */
    private BigDecimal withdrawFee;

    /**
     * 用户实际获得的提现金额
     */
    private BigDecimal withdrawActual;

    /**
     * 交易所出币地址(BTC系列为从节点，所以为空)
     */
    private String fromAddress;

    /**
     * 用户提币后收币地址
     */
    private String toAddress;

    /**
     * 审核时间
     */
    private LocalDateTime auditAt;

    /**
     * 审核操作人(管理员）
     */
    private String auditUid;

    /**
     * 审核状态，0：审核不通过 1：审核通过 2：审核中
     */
    private Boolean auditStatus;

    /**
     * 上链时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime upchainAt;

    /**
     * 上链成功时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime upchainSuccessAt;

    /**
     * 上链状态，0：失败 1：成功 2：上链后等待确认中
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Boolean upchainStatus;

}
