package com.whoiszxl.xwallet.core.client;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.xwallet.core.entity.common.FeignGetAddressRequest;
import com.whoiszxl.xwallet.core.entity.common.FeignWithdrawRequest;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @description: 钱包动态调用URL实现类
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
@Component
@Import(FeignClientsConfiguration.class)
public class WalletFeignClientService implements WalletFeignClient{

    private WalletFeignClient walletFeignClient;

    @Autowired
    public WalletFeignClientService(Decoder decoder, Encoder encoder) {
        walletFeignClient = Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .target(Target.EmptyTarget.create(WalletFeignClient.class));
    }


    @Override
    public Result<String> getNewAddress(URI baseUri, FeignGetAddressRequest feignGetAddressRequest) {
        return walletFeignClient.getNewAddress(baseUri, feignGetAddressRequest);
    }

    @Override
    public Result<String> withdraw(URI baseUri, FeignWithdrawRequest feignWithdrawRequest) {
        return walletFeignClient.withdraw(baseUri, feignWithdrawRequest);
    }
}
