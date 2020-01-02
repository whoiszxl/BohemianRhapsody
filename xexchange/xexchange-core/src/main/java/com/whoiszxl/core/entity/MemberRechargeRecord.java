package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户充值记录表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "member_recharge_record")
public class MemberRechargeRecord {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 用户ID
     */
    private String memberId;

    /**
     * 币种ID
     */
    private Integer coinId;

    /**
     * 货币名称
     */
    private String coinName;

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
     * 当前交易所处区块的高度
     */
    private Integer height;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
