package com.whoiszxl.wallet.service;

import com.whoiszxl.wallet.base.pojo.ZxlTransactions;

/**
 * <p>
 * 撮合订单 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-10-17
 */
public interface MatchService {

    /**
     * 异步调用撮合的接口功能
     * @param zxlTransactions
     */
    void matchOrder(ZxlTransactions zxlTransactions);

}
