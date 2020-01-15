package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.MemberWithdraw;

import java.util.List;

/**
 * @description: MemberWithdraw dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface MemberWithdrawDao extends BaseDao<MemberWithdraw> {

    List<MemberWithdraw> findByMemberIdAndCoinId(Long memberId, Long coinId);
}
