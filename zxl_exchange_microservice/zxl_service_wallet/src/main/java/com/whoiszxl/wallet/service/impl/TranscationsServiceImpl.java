package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.base.enums.order.BuySellEnum;
import com.whoiszxl.base.exception.ExceptionCast;
import com.whoiszxl.wallet.base.dao.TransactionsDao;
import com.whoiszxl.wallet.base.dao.UserBalanceDao;
import com.whoiszxl.wallet.base.enums.TransactionStatusEnum;
import com.whoiszxl.wallet.base.pojo.ZxlTransactions;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import com.whoiszxl.wallet.pojo.request.TransactionRequest;
import com.whoiszxl.wallet.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description: 挂单服务实现类
 * @author: whoiszxl
 * @create: 2019-10-17
 **/
@Service
public class TranscationsServiceImpl implements TransactionsService {


    @Autowired
    private UserBalanceDao userBalanceDao;

    @Autowired
    private TransactionsDao transactionsDao;


    /**
     * 添加挂单
     * 交易对买卖方向：例如交易对:BTC/USDT,currencyId为BTC的id，replaceCurrencyId是USDT的id
     * @param transactionRequest
     * @return
     */
    @Override
    public boolean addOrder(TransactionRequest transactionRequest) {
        String userId = transactionRequest.getUserId();
        //通过买卖方向获取需要交易的币种ID和交易的金额（数量）
        Object[] checkParam = getCheckParam(transactionRequest);
        Integer checkCurrencyId = (Integer) checkParam[0];
        BigDecimal transactionBalance = (BigDecimal) checkParam[1];
        Integer buyOrSell = (Integer) checkParam[2];

        //校验余额是否充足
        ZxlUserBalance userBalance = userBalanceDao.findByUserIdAndCurrencyId(userId, checkCurrencyId);
        this.checkBalanceVaild(transactionBalance, userBalance);

        //锁定余额: 减去usable_balance,加上lock_balance
        userBalanceDao.lockBalance(userId, checkCurrencyId, transactionBalance);

        //将数据添加到挂单表
        ZxlTransactions zxlTransactions = new ZxlTransactions();
        zxlTransactions.setUserId(userId);
        zxlTransactions.setCurrencyId(transactionRequest.getCurrencyId());
        zxlTransactions.setReplaceCurrencyId(transactionRequest.getReplaceCurrencyId());
        zxlTransactions.setPrice(transactionRequest.getPrice());
        zxlTransactions.setTotalCount(transactionRequest.getCount());
        zxlTransactions.setCurrentCount(transactionRequest.getCount());
        zxlTransactions.setType(buyOrSell);
        zxlTransactions.setStatus(TransactionStatusEnum.TRADE_OPEN.getValue());
        zxlTransactions.setCreatedAt(LocalDateTime.now());
        zxlTransactions.setUpdatedAt(LocalDateTime.now());
        transactionsDao.save(zxlTransactions);

        return false;
    }

    /**
     * 校验输入的金额是否合法
     * @param transactionBalance
     * @param userBalance
     */
    private void checkBalanceVaild(BigDecimal transactionBalance, ZxlUserBalance userBalance) {
        if(userBalance == null
                || userBalance.getStatus().equals(SwitchStatusEnum.STATUS_CLOSE.getStatusCode())
                || userBalance.getUsableBalance().compareTo(transactionBalance) < 0
                ) {
            ExceptionCast.cast(Result.buildError("余额不足"));
        }
    }


    /**
     * 传入参数获取到需要校验的币种ID和操作金额
     * @param transactionRequest
     * @return
     */
    private Object[] getCheckParam(TransactionRequest transactionRequest) {
        Integer checkCurrencyId = null;
        BigDecimal transactionBalance = null;
        Integer buyOrSell = 1;
        if(transactionRequest.getType().equals(BuySellEnum.BUY.getValue())) {
            checkCurrencyId = transactionRequest.getReplaceCurrencyId();
            transactionBalance = transactionRequest.getPrice().multiply(transactionRequest.getCount());
            buyOrSell = BuySellEnum.BUY.getValue();
        }

        if(transactionRequest.getType().equals(BuySellEnum.SELL.getValue())) {
            checkCurrencyId = transactionRequest.getCurrencyId();
            transactionBalance = transactionRequest.getCount();
            buyOrSell = BuySellEnum.SELL.getValue();
        }
        return new Object[]{checkCurrencyId, transactionBalance, buyOrSell};
    }
}
