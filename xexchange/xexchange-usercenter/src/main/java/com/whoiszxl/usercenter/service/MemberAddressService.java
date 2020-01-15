package com.whoiszxl.usercenter.service;

import com.whoiszxl.core.entity.MemberAddress;

/**
 * @description: 用户地址服务
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
public interface MemberAddressService {

    MemberAddress getMemberAddressByCoinId(Long memberId, Long coinId);

}
