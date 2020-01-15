package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.MemberRechargeRecord;

import java.util.List;

/**
 * @description: MemberRechargeRecord dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface MemberRechargeRecordDao extends BaseDao<MemberRechargeRecord> {

    MemberRechargeRecord findByCoinIdAndTxHashAndToAddress(Long coinId, String txHash, String address);

    List<MemberRechargeRecord> findByCoinIdAndUpchainStatus(Long coinId, Integer upchainStatus);

    List<MemberRechargeRecord> findByMemberIdAndCoinId(Long memberId, Long coinId);
}
