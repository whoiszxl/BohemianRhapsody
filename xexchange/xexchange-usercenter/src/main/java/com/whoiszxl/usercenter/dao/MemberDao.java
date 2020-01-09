package com.whoiszxl.usercenter.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.Member;

/**
 * @description: 用户dao服务
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
public interface MemberDao extends BaseDao<Member> {

    /**
     * 通过用户名和密码查询用户记录
     * @param username 用户名
     * @param password 密码
     * @return 用户实体
     */
    Member findMemberByUsernameAndPassword(String username, String password);

    Member findMemberByPhoneAndStatus(String phone, Integer status);

    Member findMemberByIdAndStatus(Long id, Integer status);
}
