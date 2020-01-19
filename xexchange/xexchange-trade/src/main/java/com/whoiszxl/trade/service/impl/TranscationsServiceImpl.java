package com.whoiszxl.trade.service.impl;

import com.whoiszxl.core.entity.MemberWallet;
import com.whoiszxl.core.entity.TradeTransactions;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.core.enums.order.BuySellEnum;
import com.whoiszxl.core.enums.wallet.TransactionStatusEnum;
import com.whoiszxl.core.exception.ExceptionCatcher;
import com.whoiszxl.core.utils.DateUtil;
import com.whoiszxl.trade.dao.MemberWalletDao;
import com.whoiszxl.trade.dao.TradeTransactionDao;
import com.whoiszxl.trade.pojo.request.TransactionRequest;
import com.whoiszxl.trade.service.MatchService;
import com.whoiszxl.trade.service.TransactionsService;
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
    private MemberWalletDao memberWalletDao;

    @Autowired
    private TradeTransactionDao tradeTransactionDao;

    @Autowired
    private MatchService matchService;


    /**
     * 添加挂单
     * 交易对买卖方向：例如交易对:BTC/USDT,coinId为BTC的id，replaceCurrencyId是USDT的id
     * @param transactionRequest
     * @return
     */
    @Override
    public boolean addOrder(TransactionRequest transactionRequest) {
         Long memberId = transactionRequest.getMemberId();
        //通过买卖方向获取需要交易的币种ID和交易的金额（数量）
        Object[] checkParam = getCheckParam(transactionRequest);
        Long checkCoinId = (Long) checkParam[0];
        BigDecimal transactionBalance = (BigDecimal) checkParam[1];
        Integer buyOrSell = (Integer) checkParam[2];

        //校验余额是否充足
        MemberWallet memberWallet = memberWalletDao.findByMemberIdAndCoinId(memberId, checkCoinId);
        this.checkBalanceVaild(transactionBalance, memberWallet);

        //锁定余额: 减去usable_balance,加上lock_balance
        memberWalletDao.lockBalance(memberId, checkCoinId, transactionBalance);

        //将数据添加到挂单表
        TradeTransactions tradeTransactions = new TradeTransactions();
        tradeTransactions.setMemberId(memberId);
        tradeTransactions.setCoinId(transactionRequest.getCoinId());
        tradeTransactions.setReplaceCoinId(transactionRequest.getReplaceCoinId());
        tradeTransactions.setPrice(transactionRequest.getPrice());
        tradeTransactions.setTotalCount(transactionRequest.getCount());
        tradeTransactions.setCurrentCount(transactionRequest.getCount());
        tradeTransactions.setType(buyOrSell);
        tradeTransactions.setStatus(TransactionStatusEnum.TRADE_OPEN.getValue());
        tradeTransactions.setCreatedAt(DateUtil.getCurrentDate());
        tradeTransactions.setUpdatedAt(DateUtil.getCurrentDate());
        TradeTransactions saveResult = tradeTransactionDao.save(tradeTransactions);

        //TODO 发送MQ过去匹配（撮合）交易,先异步调用，后改为MQ
        matchService.matchOrder(saveResult);
        return true;
    }



    /**
     * 校验输入的金额是否合法
     * @param transactionBalance
     * @param memberWallet
     */
    private void checkBalanceVaild(BigDecimal transactionBalance, MemberWallet memberWallet) {
        if(memberWallet == null
                || memberWallet.getStatus().equals(SwitchStatusEnum.STATUS_CLOSE.getStatusCode())
                || memberWallet.getUsableBalance().compareTo(transactionBalance) < 0
                ) {
            ExceptionCatcher.catchValidateEx(Result.buildError("余额不足"));
        }
    }


    /**
     * 传入参数获取到需要校验的币种ID和操作金额
     * @param transactionRequest
     * @return
     */
    private Object[] getCheckParam(TransactionRequest transactionRequest) {
        Long checkCoinId = null;
        BigDecimal transactionBalance = null;
        Integer buyOrSell = 1;
        if(transactionRequest.getType().equals(BuySellEnum.BUY.getValue())) {
            checkCoinId = transactionRequest.getReplaceCoinId();
            transactionBalance = transactionRequest.getPrice().multiply(transactionRequest.getCount());
            buyOrSell = BuySellEnum.BUY.getValue();
        }

        if(transactionRequest.getType().equals(BuySellEnum.SELL.getValue())) {
            checkCoinId = transactionRequest.getCoinId();
            transactionBalance = transactionRequest.getCount();
            buyOrSell = BuySellEnum.SELL.getValue();
        }
        return new Object[]{checkCoinId, transactionBalance, buyOrSell};
    }
}
