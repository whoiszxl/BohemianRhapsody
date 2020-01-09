package com.whoiszxl.open.service;

import com.whoiszxl.open.entity.IndexCommonNavigator;

import java.util.List;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-09-29
 */
public interface CmsConfigService {

    List<IndexCommonNavigator> findConfigList();

    String findAdBanner();
}
