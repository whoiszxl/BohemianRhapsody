package com.whoiszxl.xwallet.core.client;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.xwallet.core.entity.common.FeignGetAddressRequest;
import com.whoiszxl.xwallet.core.entity.common.FeignWithdrawRequest;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

/**
 * @description: wallet远程动态URL调用Client
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
@FeignClient(name = "walletFeignClient")
public interface WalletFeignClient {

    @RequestLine("POST")
    Result<String> getNewAddress(URI baseUri, FeignGetAddressRequest feignGetAddressRequest);

    @RequestLine("POST")
    Result<String> withdraw(URI baseUri, FeignWithdrawRequest feignWithdrawRequest);
}
