package com.whoiszxl.wallet.feign;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.wallet.base.pojo.walletvo.BtcRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: btc远程调用
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@Component(value = "btcClient")
@FeignClient("zxl-btc-wallet")
public interface BTCClient extends WalletClient{


    /**
     * 创建BTC地址
     * @param btcRequest 传递userId参数
     * @return
     */
    @Override
    @PostMapping("/btc/getNewAddress")
    String getNewAddress(@RequestBody BtcRequest btcRequest);

}
