package com.whoiszxl.open.controller;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.open.entity.IndexCommonNavigator;
import com.whoiszxl.open.service.CmsConfigService;
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
@RequestMapping("/common/config")
public class ConfigController {

    @Autowired
    private CmsConfigService cmsConfigService;


    @PostMapping("/getNavigator")
    public Result<List<IndexCommonNavigator>> getNavigator() {
        return Result.buildSuccess(cmsConfigService.findConfigList());
    }

    @PostMapping("/getAdBanner")
    public Result<String> getAdBanner() {
        return Result.buildSuccess(cmsConfigService.findAdBanner());
    }

}
