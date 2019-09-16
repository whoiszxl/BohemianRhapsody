package com.whoiszxl.wallet.service;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;
import com.whoiszxl.wallet.base.pojo.walletvo.BtcRequest;

/**
 * @description: 地址分发代理服务
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
public interface AddressDispenseService {

    /**
     * 创建或者获取一个地址，通过用户ID和币种ID去获取
     * 通过币种ID获取到币种名称动态拼接feignClient的名称，再调用，保存到数据库并返回
     * @param btcRequest userId， currencyId
     * @return
     */
    ZxlUserAddress createOrGetAddress(BtcRequest btcRequest);


}
