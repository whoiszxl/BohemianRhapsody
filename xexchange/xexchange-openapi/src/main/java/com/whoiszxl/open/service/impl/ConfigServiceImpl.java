package com.whoiszxl.open.service.impl;


import com.whoiszxl.core.entity.CmsConfig;
import com.whoiszxl.core.enums.normal.ConfigEnum;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.core.utils.FastJsonUtils;
import com.whoiszxl.open.dao.CmsConfigDao;
import com.whoiszxl.open.entity.IndexCommonNavigator;
import com.whoiszxl.open.service.CmsConfigService;
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
public class ConfigServiceImpl implements CmsConfigService {

    @Autowired
    private CmsConfigDao configDao;

    @Override
    public List<IndexCommonNavigator> findConfigList() {
        CmsConfig cmsConfig = configDao.findConfig(ConfigEnum.INDEX_TEN_NAVIGATOR.getKey(), SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return FastJsonUtils.toList(cmsConfig.getValue(), IndexCommonNavigator.class);
    }

    @Override
    public String findAdBanner() {
        CmsConfig config = configDao.findConfig(ConfigEnum.INDEX_AD_BANNER.getKey(), SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return config.getValue();
    }
}
