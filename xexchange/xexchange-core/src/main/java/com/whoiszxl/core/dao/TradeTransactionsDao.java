package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.TradeTransactions;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: TradeTransactions dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface TradeTransactionsDao extends BaseDao<TradeTransactions> {


    @Query(value = "select * from trade_transactions " +
            "where status = 0 and type != ?1 and member_id != ?2 " +
            "and coin_id = ?3 and replace_coin_id = ?4 and current_count > 0 " +
            "and price <= ?5 " +
            "order by price asc", nativeQuery = true)
    List<TradeTransactions> getBuyMatchTransactionList(Integer type, Long memberId, Long coinId, Long replaceCoinId, BigDecimal price);


    @Query(value = "select * from trade_transactions " +
            "where status = 0 and type != ?1 and member_id != ?2 " +
            "and coin_id = ?3 and replace_coin_id = ?4 and current_count > 0 " +
            "and price >= ?5 " +
            "order by price desc", nativeQuery = true)
    List<TradeTransactions> getSellMatchTransactionList(Integer type, Long memberId, Long coinId, Long replaceCoinId, BigDecimal price);

    @Transactional
    @Modifying
    @Query(value = "update trade_transactions set current_count = current_count - ?1, status = ?2 , updated_at = now() where id = ?3", nativeQuery = true)
    Integer changeCountAndStatus(BigDecimal transactionCount, Integer status, Long id);
}
