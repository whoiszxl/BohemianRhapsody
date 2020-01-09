package com.whoiszxl.open.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.CmsConfig;
import org.springframework.data.jpa.repository.Query;


/**
 * @description: banner dao
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface CmsConfigDao extends BaseDao<CmsConfig> {

    @Query(value = "select * from zxl_config where `key` = ?1 and `status` = ?2",
            countQuery = "select count(*) from zxl_currency where `key` = ?1 and `status` = ?2",
            nativeQuery = true)
    CmsConfig findConfig(String key, Integer status);
}
