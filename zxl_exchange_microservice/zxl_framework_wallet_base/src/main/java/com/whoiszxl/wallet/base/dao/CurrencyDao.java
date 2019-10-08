package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.base.pojo.multivo.ZxlAssetVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @description: currency dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface CurrencyDao extends JpaRepository<ZxlCurrency, Integer>,JpaSpecificationExecutor<ZxlCurrency> {

    @Query(value = "select * from zxl_currency where status = ?1",
    countQuery = "select count(*) from zxl_currency where status = ?1",
    nativeQuery = true)
    Page<ZxlCurrency> findByStatusOrderByCreatedAtDesc(String status, Pageable pageable);

    @Query(value = "select * from zxl_currency where status = ?1 order by sort desc",
    countQuery = "select count(*) from zxl_currency where status = ?1",
    nativeQuery = true)
    List<ZxlCurrency> findAvailCurrencyList(Integer status);

    @Query(value = "select zc.currency_name,zc.currency_decimals_num,zc.currency_buy_fee,zc.currency_sell_fee,zc.currency_url,zc.max_withdraw,zc.min_withdraw,zc.fee_withdraw," +
            "zub.all_balance,zub.lock_balance,zub.usable_balance " +
            "from zxl_currency as zc LEFT JOIN zxl_user_balance as zub on zc.id = zub.currency_id " +
            "where zub.user_id = ?1 " +
            "or user_id is null " +
            "ORDER BY zc.sort desc",
    nativeQuery = true)
    List<Map> getAssetList(String userId);
}
