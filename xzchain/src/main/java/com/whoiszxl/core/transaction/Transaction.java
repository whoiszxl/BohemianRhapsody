package com.whoiszxl.core.transaction;

import com.whoiszxl.core.block.BlockChain;
import com.whoiszxl.utils.SerializeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * @description: 交易类
 * @author: whoiszxl
 * @create: 2019-11-13
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    private static final Integer SUBSIDY = 10;
    private static final long serialVersionUID = -508451986578758290L;

    /**
     * 交易Hash
     */
    private byte[] txId;

    private TXInput[] inputs;

    private TXOutput[] outputs;

    /**
     * 设置交易ID
     */
    private void setTxId() {
        this.setTxId(DigestUtils.sha256(SerializeUtils.serialize(this)));
    }

    /**
     * 创建coinbase交易
     * @param to 收款地址
     * @param data 解锁脚本数据
     * @return
     */
    public static Transaction newCoinbaseTx(String to, String data) {
        if(StringUtils.isBlank(data)) {
            data = String.format("reward to '%s'", to);
        }
        //创建交易输出
        TXInput txInput = new TXInput(new byte[]{}, -1, data);
        //创建交易输出
        TXOutput txOutput = new TXOutput(SUBSIDY, to);
        //创建交易
        Transaction tx = new Transaction(null, new TXInput[]{txInput}, new TXOutput[]{txOutput});
        //设置交易ID
        tx.setTxId();
        return tx;
    }


    /**
     * 发送一笔交易
     * @param from 支付地址
     * @param to 接收地址
     * @param amount 交易金额
     * @param blockChain 区块链对象
     * @return
     * @throws Exception
     */
    public static Transaction newUTXOTransaction(String from, String to, Integer amount, BlockChain blockChain) throws Exception {
        SpendableOutputResult result = blockChain.findSpendableOutputs(from, amount);
        //获取到实际发送的金额,大于等于用户发送的交易金额，可能存在找零
        Integer accumulated = result.getAccumulated();
        //获取到实际发送金额中的组成部分，由以下的output们组成accumulated
        Map<String, Integer[]> unspentOuts = result.getUnspentOuts();
        if(accumulated < amount) {
            throw new Exception("ERROR: Not enough funds");
        }

        //遍历所有需要使用到的utxo,将output转换为inputs
        Iterator<Map.Entry<String, Integer[]>> iterator = unspentOuts.entrySet().iterator();
        TXInput[] txInputs = {};
        while(iterator.hasNext()) {
            Map.Entry<String, Integer[]> entry = iterator.next();
            String txIdStr = entry.getKey();
            Integer[] outIdxs = entry.getValue();
            byte[] txId = Hex.decodeHex(txIdStr);
            for (Integer outIdx : outIdxs) {
                txInputs = ArrayUtils.add(txInputs, new TXInput(txId, outIdx, from));
            }
        }


        TXOutput[] txOutputs = {};
        //构建转账的outputs,输出amount金额到to的地址上
        txOutputs = ArrayUtils.add(txOutputs, new TXOutput(amount, to));
        //如果实际发送金额大于用户转账金额，则再次构建一笔输出给自己找零
        if(accumulated > amount) {
            txOutputs = ArrayUtils.add(txOutputs, new TXOutput((accumulated - amount), from));
        }

        //构建交易对象，设置交易hash并返回
        Transaction newTx = new Transaction(null, txInputs, txOutputs);
        newTx.setTxId();
        return newTx;
    }

    /**
     * 是否为 Coinbase 交易
     *
     * @return
     */
    public boolean isCoinbase() {
        return this.getInputs().length == 1
                && this.getInputs()[0].getTxId().length == 0
                && this.getInputs()[0].getTxOutputIndex() == -1;
    }
}
