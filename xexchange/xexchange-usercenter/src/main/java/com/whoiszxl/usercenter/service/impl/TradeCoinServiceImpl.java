package com.whoiszxl.usercenter.service.impl;

import com.whoiszxl.core.dao.TradeCoinDao;
import com.whoiszxl.core.entity.TradeCoin;
import com.whoiszxl.core.entity.base.ZxlPageRequest;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.core.service.BaseService;
import com.whoiszxl.usercenter.service.TradeCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-15
 **/
@Service
public class TradeCoinServiceImpl implements TradeCoinService {

    @Autowired
    private TradeCoinDao tradeCoinDao;

    @Override
    public List<TradeCoin> queryPageByStatus(ZxlPageRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        Page<TradeCoin> tradeCoins = tradeCoinDao.findByStatusOrderByCreatedAtDesc(pageRequest.getStatus(), pageable);
        return tradeCoins.getContent();
    }

    @Override
    public List<TradeCoin> getAllCoinList() {
        return tradeCoinDao.findAvailCoinList(SwitchStatusEnum.STATUS_OPEN.getStatusCode());
    }

    @Override
    public List getAssetList(Long memberId) {
        return tradeCoinDao.getAssetList(memberId);
    }
}
