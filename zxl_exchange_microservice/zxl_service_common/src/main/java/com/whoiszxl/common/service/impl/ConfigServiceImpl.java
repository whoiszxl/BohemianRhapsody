package com.whoiszxl.common.service.impl;


import com.whoiszxl.base.enums.normal.ConfigEnum;
import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.base.utils.FastJsonUtils;
import com.whoiszxl.common.dao.BannerDao;
import com.whoiszxl.common.dao.ConfigDao;
import com.whoiszxl.common.pojo.ZxlBanner;
import com.whoiszxl.common.pojo.ZxlConfig;
import com.whoiszxl.common.pojo.config.ZxlCommonNavigator;
import com.whoiszxl.common.service.BannerService;
import com.whoiszxl.common.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页轮播表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-08
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public List<ZxlCommonNavigator> findConfigList() {
        ZxlConfig zxlConfig = configDao.findConfig(ConfigEnum.INDEX_TEN_NAVIGATOR.getKey(), SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return FastJsonUtils.toList(zxlConfig.getValue(), ZxlCommonNavigator.class);
    }

    @Override
    public String findAdBanner() {
        ZxlConfig config = configDao.findConfig(ConfigEnum.INDEX_AD_BANNER.getKey(), SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return config.getValue();
    }
}
