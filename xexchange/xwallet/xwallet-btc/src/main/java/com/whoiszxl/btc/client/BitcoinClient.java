package com.whoiszxl.btc.client;

import com.whoiszxl.btc.config.BitcoinFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @description: 查询节点信息客户端
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@FeignClient(qualifier = "btcClient", name = "btcClient", url = "${feign.client.rpc.url}", configuration = BitcoinFeignConfig.class)
public interface BitcoinClient {


    @PostMapping(value = "")
    String getRequest(@RequestBody Map<String, Object> params);
}
