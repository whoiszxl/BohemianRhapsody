package com.whoiszxl.wallet.base.pojo;

import com.whoiszxl.base.pojo.BasePojo;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户充值记录表
 * </p>
 *
 * @author whoiszxl
 * @since 2019-09-05
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "zxl_user_recharge")
public class ZxlUserRecharge {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 币种ID
     */
    private Integer currencyId;

    /**
     * 充值的交易hash
     */
    private String txHash;

    /**
     * 用户实际充值的金额
     */
    private BigDecimal rechargeActual;

    /**
     * 用户的出币地址
     */
    private String fromAddress;

    /**
     * 交易所分配给用户的唯一地址
     */
    private String toAddress;

    /**
     * 上链时间
     */
    private LocalDateTime upchainAt;

    /**
     * 上链成功时间
     */
    private LocalDateTime upchainSuccessAt;

    /**
     * 上链状态，0：失败 1：成功 2：上链后等待确认中
     */
    private Integer upchainStatus;

    /**
     * 当前交易所处区块的高度
     */
    private Integer height;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
