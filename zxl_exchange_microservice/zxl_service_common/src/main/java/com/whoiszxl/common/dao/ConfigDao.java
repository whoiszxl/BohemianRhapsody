package com.whoiszxl.common.dao;

import com.whoiszxl.common.pojo.ZxlConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


/**
 * @description: banner dao
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface ConfigDao extends JpaRepository<ZxlConfig, String>,JpaSpecificationExecutor<ZxlConfig> {

    @Query(value = "select * from zxl_config where status = ?1 and `key` = ?2",
            countQuery = "select count(*) from zxl_currency where status = ?1 and `key` = ?2",
            nativeQuery = true)
    ZxlConfig findConfigList(Integer status, String key);
}
