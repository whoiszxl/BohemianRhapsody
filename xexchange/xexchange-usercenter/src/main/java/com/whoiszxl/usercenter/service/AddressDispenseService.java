package com.whoiszxl.usercenter.service;

import com.whoiszxl.core.entity.MemberAddress;
import com.whoiszxl.xwallet.core.entity.btc.WalletRequest;

/**
 * @description: 地址分发代理服务
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
public interface AddressDispenseService {

    /**
     * 创建或者获取一个地址，通过用户ID和币种ID去获取
     * 通过币种ID获取到币种名称动态拼接feignClient的名称，再调用，保存到数据库并返回
     * @param btcRequest memberId， coinId
     * @return
     */
    MemberAddress createOrGetAddress(WalletRequest btcRequest);


}