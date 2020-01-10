package com.whoiszxl.trade.feign;

import com.whoiszxl.trade.base.pojo.walletvo.BtcRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: wallet远程调用
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
public interface WalletClient {

    String getNewAddress(@RequestBody BtcRequest btcRequest);
}
