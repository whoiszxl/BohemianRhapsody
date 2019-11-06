package com.whoiszxl.wallet.service.impl;

import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.base.enums.order.BuySellEnum;
import com.whoiszxl.wallet.base.dao.OrdersDao;
import com.whoiszxl.wallet.base.dao.TransactionsDao;
import com.whoiszxl.wallet.base.dao.UserBalanceDao;
import com.whoiszxl.wallet.base.enums.TransactionStatusEnum;
import com.whoiszxl.wallet.base.pojo.ZxlOrders;
import com.whoiszxl.wallet.base.pojo.ZxlTransactions;
import com.whoiszxl.wallet.base.pojo.ZxlUserBalance;
import com.whoiszxl.wallet.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private TransactionsDao transactionsDao;

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private UserBalanceDao userBalanceDao;


    @Async
    @Override
    public void matchOrder(ZxlTransactions zxlTransactions) {
        //查询到其他人的挂单，并且买入卖出是反方向的
        List<ZxlTransactions> data = zxlTransactions.getType().equals(BuySellEnum.BUY.getValue()) ? transactionsDao.getBuyMatchTransactionList(zxlTransactions):transactionsDao.getSellMatchTransactionList(zxlTransactions);
        if(data == null || data.isEmpty()) {
            return;
        }

        //TODO 有挂单，就有成交，有成交就有最新成交价，设置一波这个 (使用redis队列储存)

        //剩余的交易量如果再撮合了一笔价格不对等的交易，需要把差价转换为相应的剩余量
        // surplusCount = surplusCount + 差价 * 交易的数量
        Integer index = 0;
        BigDecimal surplusCount = zxlTransactions.getCurrentCount();
        while (surplusCount.compareTo(BigDecimal.ZERO) > 0 && index < data.size()) {
            ZxlTransactions rowData = data.get(index);
            BigDecimal transactionCount;

            BigDecimal priceMargin = zxlTransactions.getPrice().subtract(rowData.getPrice()).abs();


            if(rowData.getCurrentCount().compareTo(surplusCount) > 0) {
                transactionCount = surplusCount;
                surplusCount = priceMargin.multiply(transactionCount);
            }else {
                transactionCount = rowData.getCurrentCount();
                surplusCount = surplusCount.subtract(rowData.getCurrentCount());
                surplusCount = surplusCount.add(priceMargin.multiply(transactionCount));
            }

            //处理被交易方的数据
            this.handleTransaction(rowData, transactionCount);


        }


    }

    @Transactional
    public void handleTransaction(ZxlTransactions rowData, BigDecimal transactionCount) {
        //1. 更新挂单列表的数据 （被交易方的记录）
        Integer status = rowData.getCurrentCount().compareTo(transactionCount) == 0 ? TransactionStatusEnum.TRADE_CLOSE.getValue() : TransactionStatusEnum.TRADE_OPEN.getValue();
        transactionsDao.changeCountAndStatus(transactionCount, status, rowData.getId());

        //2. 新增成交订单数据
        ZxlOrders zxlOrders = new ZxlOrders();
        zxlOrders.setUserId(rowData.getUserId());
        zxlOrders.setTransactionId(rowData.getId().toString());
        zxlOrders.setCurrencyId(rowData.getCurrencyId());
        zxlOrders.setReplaceCurrencyId(rowData.getReplaceCurrencyId());
        zxlOrders.setPrice(rowData.getPrice());
        zxlOrders.setSuccessCount(transactionCount);
        zxlOrders.setCreatedAt(LocalDateTime.now());
        zxlOrders.setUpdatedAt(LocalDateTime.now());
        ordersDao.save(zxlOrders);

        //3. 增加买入余额的数量
        Integer addCurrencyId;
        BigDecimal addCurrencyCount;
        if(rowData.getType().equals(BuySellEnum.BUY.getValue())) {
            addCurrencyId = rowData.getCurrencyId();
            addCurrencyCount = transactionCount;
        }else {
            addCurrencyId = rowData.getReplaceCurrencyId();
            addCurrencyCount = transactionCount.multiply(rowData.getPrice());
        }

        // 查询balance表中记录是否存在，存在更新，不存在则新增
        ZxlUserBalance userBalance = userBalanceDao.findByUserIdAndCurrencyId(rowData.getUserId(), addCurrencyId);
        if(userBalance == null) {
            //新增
            ZxlUserBalance zxlUserBalance = new ZxlUserBalance();
            zxlUserBalance.setUserId(rowData.getUserId());
            zxlUserBalance.setCurrencyId(addCurrencyId);
            zxlUserBalance.setAllBalance(addCurrencyCount);
            zxlUserBalance.setLockBalance(BigDecimal.ZERO);
            zxlUserBalance.setUsableBalance(addCurrencyCount);
            zxlUserBalance.setStatus(SwitchStatusEnum.STATUS_OPEN.getStatusCode());
            zxlUserBalance.setCreatedAt(LocalDateTime.now());
            zxlUserBalance.setUpdatedAt(LocalDateTime.now());
            userBalanceDao.save(zxlUserBalance);
        }else {
            //更新
            userBalanceDao.addBalance(rowData.getUserId(), addCurrencyId, addCurrencyCount);
        }


        //4. 减少卖出的余额数量 （卖出一个货币，这个货币必定在表中存在）
        Integer subCurrencyId;
        BigDecimal subCurrencyCount;
        if(rowData.getType().equals(BuySellEnum.BUY.getValue())) {
            subCurrencyId = rowData.getReplaceCurrencyId();
            subCurrencyCount = transactionCount.multiply(rowData.getPrice());
        }else {
            subCurrencyId = rowData.getCurrencyId();
            subCurrencyCount = transactionCount;
        }
        userBalanceDao.subLockBalance(rowData.getUserId(), subCurrencyId, subCurrencyCount);

    }
}
