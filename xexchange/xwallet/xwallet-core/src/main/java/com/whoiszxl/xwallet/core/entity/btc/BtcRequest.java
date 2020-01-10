package com.whoiszxl.xwallet.core.entity.btc;

import lombok.Builder;
import lombok.Data;

/**
 * @description: btc请求体
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
@Data
@Builder
public class BtcRequest {

    private String memberId;

    private Integer coinId;

    private String txHash;
}
