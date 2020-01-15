package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.MemberWallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @description: MemberWallet dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface MemberWalletDao extends BaseDao<MemberWallet> {

    MemberWallet findByMemberIdAndCoinId(Long memberId, Long coinId);

    @Modifying
    @Transactional
    @Query(value = "update member_wallet set lock_balance = lock_balance + ?3, usable_balance = usable_balance - ?3 where member_id = ?1 and coin_id = ?2",
            nativeQuery = true)
    int lockBalance(Long memberId, Long checkCoinId, BigDecimal transactionBalance);


    @Modifying
    @Transactional
    @Query(value = "update member_wallet set all_balance = all_balance + ?3,usable_balance = usable_balance + ?3 where member_id = ?1 and coin_id = ?2", nativeQuery = true)
    int addBalance(Long memberId, Long addCoinId, BigDecimal addCurrencyCount);

    @Modifying
    @Transactional
    @Query(value = "update member_wallet set all_balance = all_balance - ?3,lock_balance = lock_balance - ?3 where member_id = ?1 and coin_id = ?2", nativeQuery = true)
    int subLockBalance(Long memberId, Long subCoinId, BigDecimal subCurrencyCount);
}
