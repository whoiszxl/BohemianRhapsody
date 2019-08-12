package com.whoiszxl.user.client;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.user.client.impl.CommonClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @description: Common服务的feign客户端
 * @author: whoiszxl
 * @create: 2019-08-12
 **/
@Component
@FeignClient(value = "zxl-service-common", fallback = CommonClientImpl.class)
public interface CommonClient {

    @GetMapping("/banner")
    Result<List<Object>> findAll();
}
