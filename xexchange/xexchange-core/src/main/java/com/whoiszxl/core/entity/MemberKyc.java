package com.whoiszxl.core.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 * 用户KYC认证表
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "member_kyc")
public class MemberKyc {

    @Excel(name = "主键ID", orderNum = "1", width = 25D)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 用户ID
     */
    private Long memberId;

    /**
     * 身份证ID
     */
    private String idCard;

    /**
     * 身份证正面图
     */
    private String idcardFrontUrl;

    /**
     * 身份证背面图
     */
    private String idcardReverseUrl;

    /**
     * 手持身份证图
     */
    private String idcardHoldUrl;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 审核拒绝理由
     */
    private String rejectReason;

    /**
     * 认证类型
     */
    private Integer type;

    /**
     * 审核状态: 0审核中 1审核成功 -1审核失败
     */
    private Integer auditStatus;

    /**
     * 六位视频认证随机数
     */
    private String videoRandom;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
