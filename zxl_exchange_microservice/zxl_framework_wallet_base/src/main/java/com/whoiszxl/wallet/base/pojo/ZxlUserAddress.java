package com.whoiszxl.wallet.base.pojo;

import com.whoiszxl.base.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户充值地址表
 * </p>
 *
 * @author whoiszxl
 * @since 2019-09-05
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "zxl_user_address")
public class ZxlUserAddress extends BasePojo {

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
    private Boolean status;


}
