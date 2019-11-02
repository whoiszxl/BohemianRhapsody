package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.wallet.base.pojo.ZxlTransactions;
import com.whoiszxl.wallet.service.MatchService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 撮合订单 服务类实现
 * </p>
 *
 * @author whoiszxl
 * @since 2019-10-17
 */
@Service
public class MatchServiceImpl implements MatchService {

    @Async
    @Override
    public void matchOrder(ZxlTransactions zxlTransactions) {

    }
}
