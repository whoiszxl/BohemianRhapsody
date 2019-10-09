package com.whoiszxl.common.controller;

import com.whoiszxl.common.pojo.ZxlBanner;
import com.whoiszxl.common.service.BannerService;
import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.entity.StatusCode;
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
    private BannerService bannerService;

    @GetMapping
    public Result<List<ZxlBanner>> findAll() {
        return Result.buildSuccess(bannerService.bannerList());
    }

}
