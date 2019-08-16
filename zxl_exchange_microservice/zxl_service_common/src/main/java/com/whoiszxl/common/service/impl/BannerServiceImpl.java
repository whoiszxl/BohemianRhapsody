package com.whoiszxl.common.service.impl;


import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.common.dao.BannerDao;
import com.whoiszxl.common.pojo.ZxlBanner;
import com.whoiszxl.common.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页轮播表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-08
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<ZxlBanner> bannerList() {
        List<ZxlBanner> zxlBanners = bannerDao.findByStatus(SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return zxlBanners;
    }
}
