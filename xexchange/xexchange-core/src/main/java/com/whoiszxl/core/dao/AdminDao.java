package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.Admin;

/**
 * @description: admin dao
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
public interface AdminDao extends BaseDao<Admin> {

    /**
     * 通过用户名和密码查询管理员记录
     * @param username 用户名
     * @param password 密码
     * @return 管理员实体
     */
    Admin findAdminByUsernameAndPassword(String username, String password);


}
