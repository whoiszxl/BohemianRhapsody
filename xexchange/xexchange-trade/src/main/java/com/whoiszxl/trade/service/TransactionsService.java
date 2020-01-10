package com.whoiszxl.trade.service;

import com.whoiszxl.trade.pojo.request.TransactionRequest;

/**
 * <p>
 * 挂单表 服务类
 * </p>
 *
 * @author whoiszxl
 * @since 2019-10-17
 */
public interface TransactionsService {

    boolean addOrder(TransactionRequest transactionRequest);
}
