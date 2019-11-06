package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlTransactions;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: transactions dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface TransactionsDao extends JpaRepository<ZxlTransactions, String>,JpaSpecificationExecutor<ZxlTransactions> {


    @Query(value = "select * from zxl_transactions " +
            "where status = 0 and type != #{#zxlTransactions.type} and user_id != #{#zxlTransactions.userId} " +
            "and currency_id = #{#zxlTransactions.currencyId} and replace_currency_id = #{#zxlTransactions.replaceCurrencyId} and current_count > 0 " +
            "and price <= #{#zxlTransactions.price} " +
            "order by price asc", nativeQuery = true)
    List<ZxlTransactions> getBuyMatchTransactionList(ZxlTransactions zxlTransactions);


    @Query(value = "select * from zxl_transactions " +
            "where status = 0 and type != #{#zxlTransactions.type} and user_id != #{#zxlTransactions.userId} " +
            "and currency_id = #{#zxlTransactions.currencyId} and replace_currency_id = #{#zxlTransactions.replaceCurrencyId} and current_count > 0 " +
            "and price >= #{#zxlTransactions.price} " +
            "order by price desc", nativeQuery = true)
    List<ZxlTransactions> getSellMatchTransactionList(ZxlTransactions zxlTransactions);

    @Modifying
    @Query(value = "update zxl_transactions set current_count = current_count - ?1, status = ?2 , updated_at = now() where id = ?3", nativeQuery = true)
    Integer changeCountAndStatus(BigDecimal transactionCount, Integer status, Integer id);
}
