package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.Member;

/**
 * @description: 用户dao服务
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
public interface MemberDao extends BaseDao<Member> {

    Member findMemberByPhoneAndStatus(String phone, Integer status);

    Member findMemberByIdAndStatus(Long id, Integer status);
}
