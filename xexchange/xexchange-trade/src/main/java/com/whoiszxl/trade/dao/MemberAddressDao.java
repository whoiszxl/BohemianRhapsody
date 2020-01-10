package com.whoiszxl.trade.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.MemberAddress;

/**
 * @description: member address dao层
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
public interface MemberAddressDao extends BaseDao<MemberAddress> {

    MemberAddress findMemberAddressByMemberIdAndCoinIdAndStatus(Long memberId, Long coinId, Integer status);

    MemberAddress findMemberAddressByCoinIdAndRechargeAddressAndStatus(Integer coinId, String rechargeAddress, Integer status);
}
