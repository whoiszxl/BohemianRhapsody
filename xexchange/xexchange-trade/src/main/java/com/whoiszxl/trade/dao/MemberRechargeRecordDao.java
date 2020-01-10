package com.whoiszxl.trade.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.MemberRechargeRecord;

import java.util.List;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
public interface MemberRechargeRecordDao extends BaseDao<MemberRechargeRecord> {

    MemberRechargeRecord findByCoinIdAndTxHashAndToAddress(Long coinId, String txHash, String address);

    List<MemberRechargeRecord> findByCoinIdAndUpchainStatus(Long coinId, Integer upchainStatus);

    List<MemberRechargeRecord> findByMemberIdAndCoinId(Long memberId, Long coinId);
}
