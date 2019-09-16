package com.whoiszxl.wallet.base.dao;

import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
}
