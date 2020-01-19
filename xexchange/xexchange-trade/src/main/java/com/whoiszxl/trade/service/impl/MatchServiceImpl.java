package com.whoiszxl.trade.service.impl;

import com.whoiszxl.core.entity.MemberWallet;
import com.whoiszxl.core.entity.TradeOrders;
import com.whoiszxl.core.entity.TradeTransactions;
import com.whoiszxl.core.enums.normal.SwitchStatusEnum;
import com.whoiszxl.core.enums.order.BuySellEnum;
import com.whoiszxl.core.enums.wallet.TransactionStatusEnum;
import com.whoiszxl.core.utils.DateUtil;
import com.whoiszxl.trade.dao.MemberWalletDao;
import com.whoiszxl.trade.dao.TradeOrdersDao;
import com.whoiszxl.trade.dao.TradeTransactionDao;
import com.whoiszxl.trade.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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


    @Autowired
    private TradeTransactionDao tradeTransactionDao;

    @Autowired
    private TradeOrdersDao tradeOrdersDao;

    @Autowired
    private MemberWalletDao memberWalletDao;


    @Override
    public void matchOrder(TradeTransactions transactionData) {
        //查询到其他人的挂单，并且买入卖出是反方向的
        List<TradeTransactions> data = transactionData.getType().equals(BuySellEnum.BUY.getValue()) ?
                tradeTransactionDao.getBuyMatchTransactionList(transactionData.getType(), transactionData.getMemberId(), transactionData.getCoinId(), transactionData.getReplaceCoinId(), transactionData.getPrice()) :
                tradeTransactionDao.getSellMatchTransactionList(transactionData.getType(), transactionData.getMemberId(), transactionData.getCoinId(), transactionData.getReplaceCoinId(), transactionData.getPrice());
        if(data == null || data.isEmpty()) {
            return;
        }

        //TODO 有挂单，就有成交，有成交就有最新成交价，设置一波这个 (使用redis队列储存)

        //剩余的交易量如果再撮合了一笔价格不对等的交易，需要把差价转换为相应的剩余量
        //多出来的钱需要再进行匹配，留在transactions表中，价差如1元，数量则需要乘以数量（surplusCount = surplusCount + 差价 * 交易的数量）
        //多出来的surplusCount还需要除以卖出的单价，最后公式如下（surplusCount = surplusCount + 差价 * 交易的数量 / price）
        Integer index = 0;
        BigDecimal allTransactionBalance = BigDecimal.ZERO;
        BigDecimal surplusCount = transactionData.getCurrentCount();
        while (surplusCount.compareTo(BigDecimal.ZERO) > 0 && index < data.size()) {
            TradeTransactions rowData = data.get(index);
            BigDecimal transactionCount;

            if(rowData.getCurrentCount().compareTo(surplusCount) > 0) {
                transactionCount = surplusCount;
                surplusCount = BigDecimal.ZERO;
            }else {
                transactionCount = rowData.getCurrentCount();
                surplusCount = surplusCount.subtract(rowData.getCurrentCount());
            }
            allTransactionBalance = allTransactionBalance.add(transactionCount.multiply(rowData.getPrice()));
            //处理被交易方的数据
            this.handleTransaction(rowData, transactionCount, null);
            index++;
        }

        //处理交易方的数据
        this.handleTransaction(transactionData, transactionData.getCurrentCount().subtract(surplusCount), allTransactionBalance);

        //TODO 增加currency的交易量

    }

    /**
     * 处理挂单，1.更新挂单数据 2.增加已成交订单数据 3.增减余额
     * @param rowData 需要处理的挂单对象
     * @param transactionCount 需要处理的数量
     * @param allTransactionBalance 是否是处理交易方数据   为null处理被交易方，有数据则处理交易方
     */
    @Transactional
    public void handleTransaction(TradeTransactions rowData, BigDecimal transactionCount, BigDecimal allTransactionBalance) {
        //1. 更新挂单列表的数据 （被交易方的记录）
        Integer status = rowData.getCurrentCount().compareTo(transactionCount) == 0 ? TransactionStatusEnum.TRADE_CLOSE.getValue() : TransactionStatusEnum.TRADE_OPEN.getValue();
        tradeTransactionDao.changeCountAndStatus(transactionCount, status, rowData.getId());

        //2. 新增成交订单数据
        TradeOrders tradeOrders = new TradeOrders();
        tradeOrders.setMemberId(rowData.getMemberId());
        tradeOrders.setTransactionId(rowData.getId().toString());
        tradeOrders.setCoinId(rowData.getCoinId());
        tradeOrders.setReplaceCoinId(rowData.getReplaceCoinId());
        tradeOrders.setPrice(rowData.getPrice());
        tradeOrders.setType(rowData.getType());
        tradeOrders.setSuccessCount(transactionCount);
        tradeOrders.setCreatedAt(DateUtil.getCurrentDate());
        tradeOrders.setUpdatedAt(DateUtil.getCurrentDate());
        tradeOrdersDao.save(tradeOrders);

        //3. 增加买入余额的数量
        Long addCoinId;
        BigDecimal addCoinCount;
        if(rowData.getType().equals(BuySellEnum.BUY.getValue())) {
            addCoinId = rowData.getCoinId();
            addCoinCount = transactionCount;
        }else {
            addCoinId = rowData.getReplaceCoinId();
            addCoinCount = transactionCount.multiply(rowData.getPrice());
        }

        if(allTransactionBalance != null) {
            addCoinCount = allTransactionBalance;
        }

        // 查询member_wallet表中记录是否存在，存在更新，不存在则新增
        MemberWallet userBalance = memberWalletDao.findByMemberIdAndCoinId(rowData.getMemberId(), addCoinId);
        if(userBalance == null) {
            //新增
            MemberWallet zxlUserBalance = new MemberWallet();
            zxlUserBalance.setMemberId(rowData.getMemberId());
            zxlUserBalance.setCoinId(addCoinId);
            zxlUserBalance.setAllBalance(addCoinCount);
            zxlUserBalance.setLockBalance(BigDecimal.ZERO);
            zxlUserBalance.setUsableBalance(addCoinCount);
            zxlUserBalance.setStatus(SwitchStatusEnum.STATUS_OPEN.getStatusCode());
            zxlUserBalance.setCreatedAt(DateUtil.getCurrentDate());
            zxlUserBalance.setUpdatedAt(DateUtil.getCurrentDate());
            memberWalletDao.save(zxlUserBalance);
        }else {
            //更新
            memberWalletDao.addBalance(rowData.getMemberId(), addCoinId, addCoinCount);
        }


        //4. 减少卖出的余额数量 （卖出一个货币，这个货币必定在表中存在）
        Long subCurrencyId;
        BigDecimal subCurrencyCount;
        if(rowData.getType().equals(BuySellEnum.BUY.getValue())) {
            subCurrencyId = rowData.getReplaceCoinId();
            subCurrencyCount = transactionCount.multiply(rowData.getPrice());
        }else {
            subCurrencyId = rowData.getCoinId();
            subCurrencyCount = transactionCount;
        }
        memberWalletDao.subLockBalance(rowData.getMemberId(), subCurrencyId, subCurrencyCount);

        }
}
