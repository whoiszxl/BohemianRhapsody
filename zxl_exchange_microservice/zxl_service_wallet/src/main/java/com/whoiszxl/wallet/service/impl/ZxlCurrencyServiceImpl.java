package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.base.entity.ZxlPageRequest;
import com.whoiszxl.wallet.base.dao.CurrencyDao;
import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.service.ZxlCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 币种表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-15
 */
@Service
public class ZxlCurrencyServiceImpl implements ZxlCurrencyService {

    @Autowired
    private CurrencyDao currencyDao;

    @Override
    public List<ZxlCurrency> queryPageByStatus(ZxlPageRequest zxlPageRequest) {
        Pageable pageable = PageRequest.of(zxlPageRequest.getPageNumber(), zxlPageRequest.getPageSize());
        Page<ZxlCurrency> zxlCurrencies = currencyDao.findByStatusOrderByCreatedAtDesc(zxlPageRequest.getStatus(), pageable);
        return zxlCurrencies.getContent();
    }
}
