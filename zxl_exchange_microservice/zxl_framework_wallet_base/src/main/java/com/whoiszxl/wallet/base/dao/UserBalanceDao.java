package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @description: currency dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface UserBalanceDao extends JpaRepository<ZxlUserBalance, String>,JpaSpecificationExecutor<ZxlUserBalance> {

    ZxlUserBalance findByUserIdAndCurrencyId(String userId, Integer currencyId);

    @Modifying
    @Transactional
    @Query(value = "update zxl_user_balance set lock_balance = lock_balance + ?3, usable_balance = usable_balance - ?3 where user_id = ?1 and currency_id = ?2",
            nativeQuery = true)
    int lockBalance(String userId, Integer checkCurrencyId, BigDecimal transactionBalance);


    @Modifying
    @Transactional
    @Query(value = "update zxl_user_balance set all_balance = all_balance + ?3,usable_balance = usable_balance + ?3 where user_id = ?1 and currency_id = ?2", nativeQuery = true)
    int addBalance(String userId, Integer addCurrencyId, BigDecimal addCurrencyCount);

    @Modifying
    @Transactional
    @Query(value = "update zxl_user_balance set all_balance = all_balance - ?3,lock_balance = lock_balance - ?3 where user_id = ?1 and currency_id = ?2", nativeQuery = true)
    int subLockBalance(String userId, Integer subCurrencyId, BigDecimal subCurrencyCount);
}
