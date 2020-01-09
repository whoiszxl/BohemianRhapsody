package com.whoiszxl.open.controller;

import com.whoiszxl.core.entity.CmsBanner;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.open.service.CmsBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轮播图控制器
 */
@RestController
@RequestMapping("/common/banner")
public class BannerController {

    @Autowired
    private CmsBannerService cmsBannerService;

    @GetMapping
    public Result<List<CmsBanner>> findAll() {
        return Result.buildSuccess(cmsBannerService.bannerList());
    }

}
