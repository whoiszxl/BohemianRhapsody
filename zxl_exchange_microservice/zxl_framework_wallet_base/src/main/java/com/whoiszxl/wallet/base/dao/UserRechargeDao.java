package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import com.whoiszxl.wallet.base.pojo.ZxlUserRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: currency dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface UserRechargeDao extends JpaRepository<ZxlUserRecharge, String>,JpaSpecificationExecutor<ZxlUserRecharge> {

    ZxlUserRecharge findByCurrencyIdAndTxHashAndToAddress(Integer currencyId, String txHash, String address);

    List<ZxlUserRecharge> findByCurrencyIdAndUpchainStatus(Integer currencyId, Integer upchainStatus);
}
