package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: useraddress dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface UserAddressDao extends JpaRepository<ZxlUserAddress, String>,JpaSpecificationExecutor<ZxlUserAddress> {


    ZxlUserAddress findByUserIdAndCurrencyIdAndStatus(String userId, Integer currencyId, Integer status);

}
