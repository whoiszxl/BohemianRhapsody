package com.whoiszxl.btc.job;

import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import com.whoiszxl.base.utils.IdWorker;
import com.whoiszxl.btc.pojo.ListTransactionResponse;
import com.whoiszxl.btc.pojo.TransactionInfoResponse;
import com.whoiszxl.btc.service.BitcoinService;
import com.whoiszxl.wallet.base.dao.CurrencyDao;
import com.whoiszxl.wallet.base.dao.UserAddressDao;
import com.whoiszxl.wallet.base.dao.UserRechargeDao;
import com.whoiszxl.wallet.base.enums.RechargeUpchainStatusEnum;
import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;
import com.whoiszxl.wallet.base.pojo.ZxlUserRecharge;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

/**
 * @description: 充值定时任务
 * @author: whoiszxl
 * @create: 2019-08-31
 **/
@Component
@Slf4j
public class RechargeJob {

    @Autowired
    private BitcoinService bitcoinService;

    @Autowired
    private UserRechargeDao userRechargeDao;

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CurrencyDao currencyDao;

    //TODO 从redis中拿到币种ID
    Integer currencyId = 1;

    @Scheduled(fixedDelay = 10000)
    public void scanRecharge() {

        //从链上拿到所有的充值记录遍历
        List<ListTransactionResponse> listTransactionResponses = bitcoinService.listTransactions();
        for (ListTransactionResponse chainTransaction : listTransactionResponses) {

            //筛选交易类别为接收的
            if(!StringUtils.equals("receive", chainTransaction.getCategory())) {
                continue;
            }

            //判断充值的地址是否是数据库中生成的
            ZxlUserAddress userAddress = userAddressDao.findByCurrencyIdAndRechargeAddressAndStatus(currencyId, chainTransaction.getAddress(), SwitchStatusEnum.STATUS_OPEN.getStatusCode());
            if(userAddress == null) {
                continue;
            }

            //判断链上的数据是否在数据库中存在
            ZxlUserRecharge userDbRecharge = userRechargeDao.findByCurrencyIdAndTxHashAndToAddress(currencyId, chainTransaction.getTxid(), chainTransaction.getAddress());
            if(userDbRecharge != null) {
                continue;
            }

            //入库
            ZxlUserRecharge dbParams = ZxlUserRecharge.builder()
                    .id(idWorker.nextId() + "")
                    .userId(userAddress.getUserId())
                    .currencyId(currencyId)
                    .txHash(chainTransaction.getTxid())
                    .rechargeActual(chainTransaction.getAmount())
                    .fromAddress(null)
                    .toAddress(chainTransaction.getAddress())
                    .upchainAt(LocalDateTime.ofEpochSecond(chainTransaction.getTime(), 0, ZoneOffset.ofHours(8)))
                    .upchainSuccessAt(null)
                    .upchainStatus(RechargeUpchainStatusEnum.STATUS_UN_CONFIRM.getCode())
                    .height(chainTransaction.getBlockindex())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRechargeDao.save(dbParams);

        }

    }

    @Scheduled(fixedDelay = 20000)
    public void confirmRecharge() {
        Optional<ZxlCurrency> currencyOptional = currencyDao.findById(currencyId);
        if(!currencyOptional.isPresent()) {
            return;
        }
        ZxlCurrency btcCurrency = currencyOptional.get();
        //从数据库拿到所有已在链上但没有确认的交易(status = 2  上链后等待确认中)
        List<ZxlUserRecharge> zxlUserRechargeList = userRechargeDao.findByCurrencyIdAndUpchainStatus(currencyId, 2);
        for (ZxlUserRecharge zxlUserRecharge : zxlUserRechargeList) {
            TransactionInfoResponse transaction = bitcoinService.getTransaction(zxlUserRecharge.getTxHash());
            //校验确认数
            if(transaction.getConfirmations() < btcCurrency.getConfirms()) {
                continue;
            }
            //更新充值记录
            zxlUserRecharge.setUpchainSuccessAt(LocalDateTime.now());
            zxlUserRecharge.setUpchainStatus(RechargeUpchainStatusEnum.STATUS_SUCCESS.getCode());
            userRechargeDao.save(zxlUserRecharge);
        }

    }
}
