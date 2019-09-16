package com.whoiszxl.wallet.base.pojo.walletvo;

import lombok.Builder;
import lombok.Data;

/**
 * @description: btc
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@Data
@Builder
public class BtcRequest {

    private String userId;

    private Integer currencyId;

    private String txHash;
}
