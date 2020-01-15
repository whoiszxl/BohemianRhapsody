package com.whoiszxl.xwallet.core.entity.btc;

import lombok.Builder;
import lombok.Data;

/**
 * @description: 钱包调用请求体
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
@Data
@Builder
public class WalletRequest {

    private Long memberId;

    private Long coinId;

    private String coinName;

    private String txHash;
}
