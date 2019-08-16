package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.wallet.dao.CurrencyDao;
import com.whoiszxl.wallet.pojo.ZxlCurrency;
import com.whoiszxl.wallet.pojo.request.PageRequest;
import com.whoiszxl.wallet.service.ZxlCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<ZxlCurrency> queryPageByStatus(PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        Page<ZxlCurrency> zxlCurrencies = currencyDao.queryByStatusOrderByCreatedAtDesc(pageRequest.getStatus(), pageable);
        return zxlCurrencies.getContent();
    }
}
