package com.whoiszxl.trade.service;

import com.whoiszxl.core.entity.MemberRechargeRecord;
import com.whoiszxl.core.entity.MemberWithdraw;

import java.util.List;

/**
 * 充提服务
 */
public interface MemberWithdrawRecordService {

    List<MemberRechargeRecord> getRechargeListByMemberIdAndCoinId(Long memberId, Long coinId);

    List<MemberWithdraw> getWithdrawListByMemberIdAndCoinId(Long memberId, Long coinId);

}
