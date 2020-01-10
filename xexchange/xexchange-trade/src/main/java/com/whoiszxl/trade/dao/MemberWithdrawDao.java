package com.whoiszxl.trade.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.MemberWithdraw;

import java.util.List;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
public interface MemberWithdrawDao extends BaseDao<MemberWithdraw> {

    List<MemberWithdraw> findByMemberIdAndCoinId(Long memberId, Long coinId);

}
