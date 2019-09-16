package com.whoiszxl.wallet.base.service.impl;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.wallet.base.dao.UserAddressDao;
import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;
import com.whoiszxl.wallet.base.service.ZxlUserAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 币种表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-15
 */
@Service
public class ZxlUserAddressServiceImpl implements ZxlUserAddressService {

    @Autowired
    private UserAddressDao userAddressDao;

    @Override
    public ZxlUserAddress getUserAddressByCurrencyId(String userId, Integer currencyId) {
        ZxlUserAddress zxlUserAddress = userAddressDao.findByUserIdAndCurrencyIdAndStatus(userId, currencyId, SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return zxlUserAddress;
    }
}
