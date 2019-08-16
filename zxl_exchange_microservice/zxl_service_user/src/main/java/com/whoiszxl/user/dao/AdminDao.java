package com.whoiszxl.user.dao;

import com.whoiszxl.user.pojo.ZxlAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface AdminDao extends JpaRepository<ZxlAdmin, String>,JpaSpecificationExecutor<ZxlAdmin> {
}
