package com.whoiszxl.open.service;

import com.whoiszxl.core.entity.CmsBanner;

import java.util.List;

/**
 * <p>
 * 首页轮播表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-08
 */
public interface CmsBannerService {

    List<CmsBanner> bannerList();
}
