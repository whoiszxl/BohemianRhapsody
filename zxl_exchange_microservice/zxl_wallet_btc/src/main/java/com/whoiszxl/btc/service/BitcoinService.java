package com.whoiszxl.btc.service;

import com.whoiszxl.base.entity.RpcParams;
import com.whoiszxl.base.utils.FastJsonUtils;
import com.whoiszxl.btc.client.BitcoinClient;
import com.whoiszxl.btc.pojo.RpcBaseResponse;
import com.whoiszxl.btc.pojo.ListTransactionResponse;
import com.whoiszxl.btc.pojo.TransactionInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @description: bitcoin服务层
 * @author: whoiszxl
 * @create: 2019-08-31
 **/
@Service
@Slf4j
public class BitcoinService {

    @Autowired
    private BitcoinClient bitcoinClient;

    /**
     * 创建新的BTC钱包地址
     * @param userId 用户ID
     * @return 地址对象
     */
    public String getNewAddress(String userId) {
        Map params = RpcParams.builder().method("getnewaddress").params(new Object[]{userId}).build().get();
        String addressStr = bitcoinClient.getRequest(params);
        RpcBaseResponse rpcBaseResponse = FastJsonUtils.from(addressStr, RpcBaseResponse.class);
        if(rpcBaseResponse.getError() != null) {
            log.warn("创建钱包地址失败，用户ID为：{}，节点报错信息为：{}", userId, rpcBaseResponse.getError());
            return null;
        }
        return rpcBaseResponse.getResult();
    }

    /**
     * 获取节点钱包中所有余额
     * @return
     */
    public String getBalance() {
        Map params = RpcParams.builder().method("getbalance").params(new Object[]{"*"}).build().get();
        String balanceStr = bitcoinClient.getRequest(params);
        RpcBaseResponse rpcBaseResponse = FastJsonUtils.from(balanceStr, RpcBaseResponse.class);
        if(rpcBaseResponse.getError() != null) {
            log.warn("获取钱包余额失败，节点报错信息为：{}", rpcBaseResponse.getError());
            return null;
        }
        return rpcBaseResponse.getResult();
    }


    /**
     * 查询最近发生的钱包交易
     * @param account 钱包账户名，默认：*
     * @param count 要提取的交易数量，默认值：10
     * @param skip 要跳过的交易数量，默认值：0
     * @return
     */
    public ListTransactionResponse listTransactions(String account, int count, int skip) {
        Map params = RpcParams.builder().method("listtransactions").params(new Object[]{account, count, skip}).build().get();
        String transactionsStr = bitcoinClient.getRequest(params);
        RpcBaseResponse rpcBaseResponse = FastJsonUtils.from(transactionsStr, RpcBaseResponse.class);
        if(rpcBaseResponse.getError() != null) {
            log.warn("获取钱包交易列表失败，节点报错信息为：{}", rpcBaseResponse.getError());
            return null;
        }
        ListTransactionResponse transactionResponse = FastJsonUtils.from(rpcBaseResponse.getResult(), ListTransactionResponse.class);
        return transactionResponse;
    }

    /**
     * 查询最近发生的钱包交易,默认查询所有账户，查询1000条，跳过0条
     * @return
     */
    public ListTransactionResponse listTransactions() {
        return listTransactions("*", 1000, 0);
    }


    /**
     * 通过txHash获取交易详情
     * @param txHash 交易hash
     * @return
     */
    public TransactionInfoResponse getTransaction(String txHash) {
        Map params = RpcParams.builder().method("gettransaction").params(new Object[]{txHash}).build().get();
        String transactionsStr = bitcoinClient.getRequest(params);
        RpcBaseResponse rpcBaseResponse = FastJsonUtils.from(transactionsStr, RpcBaseResponse.class);
        if(rpcBaseResponse.getError() != null) {
            log.warn("通过txHash获取交易详情失败，节点报错信息为：{}", rpcBaseResponse.getError());
            return null;
        }
        TransactionInfoResponse transactionResponse = FastJsonUtils.from(rpcBaseResponse.getResult(), TransactionInfoResponse.class);
        return transactionResponse;
    }


    /**
     * 发送交易，返回是否成功的判断
     * @param toAddress 转入地址
     * @param amount 转入金额
     * @return 是否成功
     */
    public boolean sendToAddress(String toAddress, BigDecimal amount) {
        Map params = RpcParams.builder().method("sendtoaddress").params(new Object[]{toAddress, amount, ""}).build().get();
        String sendStr = bitcoinClient.getRequest(params);
        RpcBaseResponse rpcBaseResponse = FastJsonUtils.from(sendStr, RpcBaseResponse.class);
        if(rpcBaseResponse.getError() != null) {
            log.warn("发送交易失败，转入地址为{}，金额为：{}，节点报错信息为：{}", toAddress, amount, rpcBaseResponse.getError());
            return false;
        }
        return true;
    }

}
