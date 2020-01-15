package com.whoiszxl.usercenter.service;

import com.whoiszxl.core.entity.TradeCoin;
import com.whoiszxl.core.entity.base.ZxlPageRequest;

import java.util.List;

/**
 * @description: 币种服务
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
public interface TradeCoinService {

    /**
     * 通过开关状态查找币种列表
     * @param pageRequest
     * @return
     */
    List<TradeCoin> queryPageByStatus(ZxlPageRequest pageRequest);

    List<TradeCoin> getAllCoinList();

    List getAssetList(Long memberId);
}
