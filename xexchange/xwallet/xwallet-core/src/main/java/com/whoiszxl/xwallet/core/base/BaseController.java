package com.whoiszxl.xwallet.core.base;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.xwallet.core.entity.common.FeignGetAddressRequest;
import com.whoiszxl.xwallet.core.entity.common.FeignWithdrawRequest;

import java.math.BigDecimal;

/**
 * @description: 钱包控制器接口规范
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
public interface BaseController {

    /**
     * 获取当前区块高度
     * @return
     */
    Result blockHeight();

    Result getNewAddress(FeignGetAddressRequest feignGetAddressRequest);

    Result withdraw(FeignWithdrawRequest withdrawRequest);

    Result transfer(FeignWithdrawRequest withdrawRequest);

    Result getBalance();

}
