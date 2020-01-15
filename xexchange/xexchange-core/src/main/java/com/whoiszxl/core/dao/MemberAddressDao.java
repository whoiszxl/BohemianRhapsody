package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.MemberAddress;

/**
 * @description: member address dao
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
public interface MemberAddressDao extends BaseDao<MemberAddress> {

    MemberAddress findByMemberIdAndCoinIdAndStatus(Long memberId, Long coinId, Integer status);

    MemberAddress findByCoinIdAndRechargeAddressAndStatus(Long coinId, String rechargeAddress, Integer status);
}
