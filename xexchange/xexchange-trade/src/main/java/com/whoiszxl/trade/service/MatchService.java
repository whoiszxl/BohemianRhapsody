package com.whoiszxl.trade.service;

import com.whoiszxl.core.entity.TradeTransactions;

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
     * @param tradeTransactions
     */
    void matchOrder(TradeTransactions tradeTransactions);

}
