package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.wallet.base.dao.UserRechargeDao;
import com.whoiszxl.wallet.base.dao.UserWithdrawDao;
import com.whoiszxl.wallet.base.pojo.ZxlUserRecharge;
import com.whoiszxl.wallet.base.pojo.ZxlUserWithdraw;
import com.whoiszxl.wallet.service.RechargeWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 充提服务实现
 * @author: whoiszxl
 * @create: 2019-10-16
 **/
@Service
public class RechargeWithdrawServiceImpl implements RechargeWithdrawService {

    @Autowired
    private UserRechargeDao userRechargeDao;

    @Autowired
    private UserWithdrawDao userWithdrawDao;

    @Override
    public List<ZxlUserRecharge> getRechargeListByUserIdAndCurrencyId(String userId, Integer currencyId) {
         return userRechargeDao.findByUserIdAndCurrencyId(userId, currencyId);
    }

    @Override
    public List<ZxlUserWithdraw> getWithdrawListByUserIdAndCurrencyId(String userId, Integer currencyId) {
        return userWithdrawDao.findByUserIdAndCurrencyId(userId, currencyId);
    }
}
