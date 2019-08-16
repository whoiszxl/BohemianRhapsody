package com.whoiszxl.wallet.dao;

import com.whoiszxl.wallet.pojo.ZxlCurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: currency dao层
 * @author: whoiszxl
 * @create: 2019-08-16
 **/
public interface CurrencyDao extends JpaRepository<ZxlCurrency, Integer>,JpaSpecificationExecutor<ZxlCurrency> {

    Page<ZxlCurrency> queryByStatusOrderByCreatedAtDesc(Integer status, Pageable pageable);
}
