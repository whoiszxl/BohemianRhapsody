package com.whoiszxl.user.dao;

import com.whoiszxl.user.pojo.ZxlUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface UserDao extends JpaRepository<ZxlUser, String>,JpaSpecificationExecutor<ZxlUser> {
    ZxlUser findByPhoneAndStatus(String phone, Integer status);

    ZxlUser findByIdAndStatus(String id, Integer status);
}
