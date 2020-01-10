package com.whoiszxl.trade.service.impl;

import com.whoiszxl.core.entity.MemberRechargeRecord;
import com.whoiszxl.core.entity.MemberWithdraw;
import com.whoiszxl.trade.dao.MemberRechargeRecordDao;
import com.whoiszxl.trade.dao.MemberWithdrawDao;
import com.whoiszxl.trade.service.MemberWithdrawRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 充提服务实现
 * @author: whoiszxl
 * @create: 2019-10-16
 **/
@Service
public class RechargeWithdrawServiceImpl implements MemberWithdrawRecordService {

    @Autowired
    private MemberRechargeRecordDao memberRechargeRecordDao;

    @Autowired
    private MemberWithdrawDao memberWithdrawDao;

    @Override
    public List<MemberRechargeRecord> getRechargeListByMemberIdAndCoinId(Long memberId, Long coinId) {
         return memberRechargeRecordDao.findByMemberIdAndCoinId(memberId, coinId);
    }

    @Override
    public List<MemberWithdraw> getWithdrawListByMemberIdAndCoinId(Long memberId, Long coinId) {
        return memberWithdrawDao.findByMemberIdAndCoinId(memberId, coinId);
    }
}
