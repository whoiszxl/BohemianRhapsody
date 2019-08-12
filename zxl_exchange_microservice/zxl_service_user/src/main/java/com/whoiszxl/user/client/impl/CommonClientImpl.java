package com.whoiszxl.user.client.impl;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.user.client.CommonClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: CommonClient熔断器
 * @author: whoiszxl
 * @create: 2019-08-12
 **/
@Component
public class CommonClientImpl implements CommonClient {
    @Override
    public Result<List<Object>> findAll() {
        return Result.buildError("hystrix start.");
    }
}
