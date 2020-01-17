package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户提现记录表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "member_withdraw")
public class MemberWithdraw {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 用户ID
     */
    private Long memberId;

    /**
     * 币种ID
     */
    private Long coinId;

    /**
     * 货币名称
     */
    private String coinName;

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
    private Date auditAt;

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
    private Date upchainAt;

    /**
     * 上链成功时间
     */
    private Date upchainSuccessAt;

    /**
     * 上链状态，0：失败 1：成功 2：上链后等待确认中
     */
    private Boolean upchainStatus;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
