package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户钱包余额表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "member_wallet")
public class MemberWallet {

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
     * 总金额数量
     */
    private BigDecimal allBalance;

    /**
     * 冻结金额数量
     */
    private BigDecimal lockBalance;

    /**
     * 可用金额数量
     */
    private BigDecimal usableBalance;

    /**
     * 钱包，0：关闭 1：开启
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
