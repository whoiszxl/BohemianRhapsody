package com.whoiszxl.wallet.base.service.impl;

import com.whoiszxl.base.entity.ZxlPageRequest;
import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.wallet.base.dao.CurrencyDao;
import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.base.service.ZxlCurrencyService;
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

    @Override
    public List<ZxlCurrency> getAllCurrencyList() {
        List<ZxlCurrency> availCurrencyList = currencyDao.findAvailCurrencyList(SwitchStatusEnum.STATUS_OPEN.getStatusCode());
        return availCurrencyList;
    }
}
