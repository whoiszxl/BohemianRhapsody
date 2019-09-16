package com.whoiszxl.wallet.feign;

import com.whoiszxl.wallet.base.pojo.walletvo.BtcRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: wallet远程调用
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
public interface WalletClient {

    String getNewAddress(@RequestBody BtcRequest btcRequest);
}
