package com.whoiszxl.common.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.common.pojo.config.ZxlCommonNavigator;
import com.whoiszxl.common.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 配置控制器
 * @author: whoiszxl
 * @create: 2019-09-29
 **/
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;


    @PostMapping("/getNavigator")
    public Result<List<ZxlCommonNavigator>> getNavigator() {
        return Result.buildSuccess(configService.findConfigList());
    }

    @PostMapping("/getAdBanner")
    public Result<String> getAdBanner() {
        return Result.buildSuccess(configService.findAdBanner());
    }

}
