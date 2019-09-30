package com.whoiszxl.common.service;

import com.whoiszxl.common.pojo.config.ZxlCommonNavigator;

import java.util.List;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-09-29
 */
public interface ConfigService {

    List<ZxlCommonNavigator> findConfigList();

    String findAdBanner();
}
