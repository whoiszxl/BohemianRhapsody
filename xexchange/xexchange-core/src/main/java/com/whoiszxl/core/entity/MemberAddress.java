package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 用户充值地址表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Builder
@Table(name = "member_address")
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddress {

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
     * 充值地址
     */
    private String rechargeAddress;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 密码
     */
    private String password;

    /**
     * keystore
     */
    private String keystore;

    /**
     * 钱包状态，0：关闭 1：开启
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
