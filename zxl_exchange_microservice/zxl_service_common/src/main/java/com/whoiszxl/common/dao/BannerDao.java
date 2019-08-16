package com.whoiszxl.common.dao;

import com.whoiszxl.common.pojo.ZxlBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: banner dao
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface BannerDao extends JpaRepository<ZxlBanner, String>,JpaSpecificationExecutor<ZxlBanner> {

    List<ZxlBanner> findByStatus(Integer status);
}
