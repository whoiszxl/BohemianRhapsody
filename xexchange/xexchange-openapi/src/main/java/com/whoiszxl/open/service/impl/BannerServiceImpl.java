package com.whoiszxl.open.service.impl;


import com.whoiszxl.core.entity.CmsBanner;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.open.dao.CmsBannerDao;
import com.whoiszxl.open.service.CmsBannerService;
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
public class BannerServiceImpl implements CmsBannerService {

    @Autowired
    private CmsBannerDao cmsBannerDao;

    @Override
    public List<CmsBanner> bannerList() {
        return cmsBannerDao.findByStatus(SwitchStatusEnum.STATUS_OPEN.getStatusCode());
    }
}
