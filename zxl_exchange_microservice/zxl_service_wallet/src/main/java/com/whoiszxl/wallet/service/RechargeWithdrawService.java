package com.whoiszxl.wallet.service;

import com.whoiszxl.wallet.base.pojo.ZxlUserRecharge;
import com.whoiszxl.wallet.base.pojo.ZxlUserWithdraw;

import java.util.List;

/**
 * 充提服务
 */
public interface RechargeWithdrawService {

    List<ZxlUserRecharge> getRechargeListByUserIdAndCurrencyId(String userId, Integer currencyId);

    List<ZxlUserWithdraw> getWithdrawListByUserIdAndCurrencyId(String userId, Integer currencyId);

}
