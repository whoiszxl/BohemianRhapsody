package com.whoiszxl.trade.dao;

import com.whoiszxl.core.dao.BaseDao;
import com.whoiszxl.core.entity.TradeCoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
public interface TradeCoinDao extends BaseDao<TradeCoin> {

    @Query(value = "select * from cms_coin where status = ?1",
            countQuery = "select count(*) from cms_coin where status = ?1",
            nativeQuery = true)
    Page<TradeCoin> findByStatusOrderByCreatedAtDesc(String status, Pageable pageable);

    @Query(value = "select * from cms_coin where status = ?1 order by sort desc",
            countQuery = "select count(*) from cms_coin where status = ?1",
            nativeQuery = true)
    List<TradeCoin> findAvailCoinList(Integer status);

    @Query(value = "select zc.id,zc.coin_name,zc.coin_decimals_num,zc.coin_buy_fee,zc.coin_sell_fee,zc.coin_url,zc.max_withdraw,zc.min_withdraw,zc.fee_withdraw," +
            "zub.all_balance,zub.lock_balance,zub.usable_balance " +
            "from cms_coin as zc LEFT JOIN zxl_user_balance as zub on zc.id = zub.coin_id " +
            "where zub.member_id = ?1 " +
            "or member_id is null " +
            "ORDER BY zc.sort desc",
            nativeQuery = true)
    List<Map> getAssetList(String memberId);
}
