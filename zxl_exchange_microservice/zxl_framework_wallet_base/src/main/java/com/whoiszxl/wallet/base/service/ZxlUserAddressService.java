package com.whoiszxl.wallet.base.service;

import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;

/**
 * <p>
 * 币种表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-15
 */
public interface ZxlUserAddressService {


    ZxlUserAddress getUserAddressByCurrencyId(String userId, Integer currencyId);

}
