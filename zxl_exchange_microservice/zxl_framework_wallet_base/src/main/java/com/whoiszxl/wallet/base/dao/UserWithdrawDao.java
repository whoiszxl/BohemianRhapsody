package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import com.whoiszxl.wallet.base.pojo.ZxlUserWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: currency dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface UserWithdrawDao extends JpaRepository<ZxlUserWithdraw, String>,JpaSpecificationExecutor<ZxlUserWithdraw> {

    List<ZxlUserWithdraw> findByUserIdAndCurrencyId(String userId, Integer currencyId);
}
