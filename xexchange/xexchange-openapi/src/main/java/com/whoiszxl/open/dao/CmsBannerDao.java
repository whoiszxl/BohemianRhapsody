package com.whoiszxl.open.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.CmsBanner;

import java.util.List;

/**
 * @description: banner dao
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface CmsBannerDao extends BaseDao<CmsBanner> {

    List<CmsBanner> findByStatus(Integer status);
}
