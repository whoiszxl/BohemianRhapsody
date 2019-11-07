package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlTransactions;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: transactions dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface TransactionsDao extends JpaRepository<ZxlTransactions, String>,JpaSpecificationExecutor<ZxlTransactions> {


    @Query(value = "select * from zxl_transactions " +
            "where status = 0 and type != ?1 and user_id != ?2 " +
            "and currency_id = ?3 and replace_currency_id = ?4 and current_count > 0 " +
            "and price <= ?5 " +
            "order by price asc", nativeQuery = true)
    List<ZxlTransactions> getBuyMatchTransactionList(Integer type, String userId, Integer currencyId, Integer replaceCurrencyId, BigDecimal price);


    @Query(value = "select * from zxl_transactions " +
            "where status = 0 and type != ?1 and user_id != ?2 " +
            "and currency_id = ?3 and replace_currency_id = ?4 and current_count > 0 " +
            "and price >= ?5 " +
            "order by price desc", nativeQuery = true)
    List<ZxlTransactions> getSellMatchTransactionList(Integer type, String userId, Integer currencyId, Integer replaceCurrencyId, BigDecimal price);

    @Transactional
    @Modifying
    @Query(value = "update zxl_transactions set current_count = current_count - ?1, status = ?2 , updated_at = now() where id = ?3", nativeQuery = true)
    Integer changeCountAndStatus(BigDecimal transactionCount, Integer status, Integer id);
}
