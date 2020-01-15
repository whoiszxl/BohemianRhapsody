package com.whoiszxl.core.dao;

import com.whoiszxl.core.entity.TradeCoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @description: TradeCoin dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface TradeCoinDao extends BaseDao<TradeCoin> {

    @Query(value = "select * from trade_coin where status = ?1",
    countQuery = "select count(*) from trade_coin where status = ?1",
    nativeQuery = true)
    Page<TradeCoin> findByStatusOrderByCreatedAtDesc(String status, Pageable pageable);

    @Query(value = "select * from trade_coin where status = ?1 order by sort desc",
    countQuery = "select count(*) from trade_coin where status = ?1",
    nativeQuery = true)
    List<TradeCoin> findAvailCoinList(Integer status);

    @Query(value = "select tc.id,tc.coin_name,tc.coin_decimals_num,tc.coin_buy_fee,tc.coin_sell_fee,tc.coin_url,tc.max_withdraw,tc.min_withdraw,tc.fee_withdraw," +
            "mw.all_balance,mw.lock_balance,mw.usable_balance " +
            "from trade_coin as tc LEFT JOIN member_wallet as mw on tc.id = mw.coin_id " +
            "where mw.member_id = ?1 " +
            "or member_id is null " +
            "ORDER BY tc.sort desc",
    nativeQuery = true)
    List<Map> getAssetList(Long memberId);
}
