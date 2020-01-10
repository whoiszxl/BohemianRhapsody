package com.whoiszxl.xwallet.core.client;

import com.whoiszxl.xwallet.core.entity.btc.BtcRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: wallet地址生成远程调用接口定义
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
public interface WalletClient {

    String getNewAddress(@RequestBody BtcRequest btcRequest);
}
