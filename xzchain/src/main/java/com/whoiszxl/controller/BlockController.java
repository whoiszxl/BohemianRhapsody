package com.whoiszxl.controller;

import com.whoiszxl.core.block.Block;
import com.whoiszxl.core.block.BlockChain;
import com.whoiszxl.core.transaction.TXOutput;
import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.entity.Result;
import com.whoiszxl.utils.RocksDBUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @description: 区块控制器
 * @author: whoiszxl
 * @create: 2019-11-19
 **/
@Slf4j
@RestController
@RequestMapping("/block")
public class BlockController {


    /**
     * 创建区块链
     * @return 结果
     */
    @PostMapping("/createBlockChain")
    public Result createBlockChainWithGenesisBlock(@RequestBody Map<String, String> params) {
        if(StringUtils.isBlank(params.get("address"))) {
            return Result.buildError(1, "创建区块链需要先传入一个地址");
        }
        BlockChain.getOrCreateBlockChain(params.get("address"));
        return Result.buildSuccess();
    }

    @PostMapping("/getBalance")
    public Result getBalance(@RequestBody Map<String, String> params) throws Exception {
        String address = params.get("address");
        if(StringUtils.isBlank(address)) {
            return Result.buildError(1, "地址参数错误");
        }
        Integer balance = this.getBalance(address);
        return Result.buildSuccess(balance);
    }


    private Integer getBalance(String address) throws Exception {
        BlockChain blockChain = BlockChain.getOrCreateBlockChain(address);
        TXOutput[] utxo = blockChain.findUTXO(address);
        Integer balance = 0;
        if(utxo != null && utxo.length > 0) {
            for (TXOutput txOutput : utxo) {
                balance += txOutput.getValue();
            }
        }
        log.info("【获取地址余额】地址为：{}, 余额为：{}", address, balance);
        return balance;
    }


    @PostMapping("/send")
    public Result send(@RequestBody Map<String, String> params) throws Exception {
        String fromAddress = params.get("from");
        String toAddress = params.get("to");
        String sendAmount = params.get("amount");
        if(StringUtils.isBlank(fromAddress) || StringUtils.isBlank(toAddress) || StringUtils.isBlank(sendAmount)) {
            return Result.buildError(1, "发送交易参数确实");
        }
        this.send(fromAddress, toAddress, new Integer(sendAmount));
        return Result.buildSuccess();
    }

    private void send(String fromAddress, String toAddress, Integer amount) throws Exception {
        BlockChain blockChain = BlockChain.getOrCreateBlockChain(fromAddress);
        Transaction transaction = Transaction.newUTXOTransaction(fromAddress, toAddress, amount, blockChain);
        ArrayList<Transaction> mineParam = new ArrayList<Transaction>();
        mineParam.add(transaction);
        blockChain.mineBlock(mineParam);
        RocksDBUtils.getInstance().closeDB();
        log.info("【发送交易】发送交易成功");
    }

    /**
     * 添加区块
     *
     */
    @PostMapping("/addBlock")
    public Result addBlock() {
        BlockChain blockchain = BlockChain.getOrCreateBlockChain("aaa");
        List<Transaction> transactions = new LinkedList<>();
        try {
            blockchain.addBlock(transactions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.buildSuccess();
    }


    /**
     * 打印出区块链中的所有区块
     */
    @PostMapping("/printChain")
    public Result<List<Block>> printChain() {
        BlockChain blockchain = BlockChain.getOrCreateBlockChain("aaa");
        BlockChain.BlockChainIterator iterator = blockchain.getBlockchainIterator();
        List<Block> results = new LinkedList<>();
        while (iterator.hashNext()) {
            Block block = iterator.next();
            System.out.println("第" + block.getHeight() + "个区块信息：");
            results.add(block);
        }

        return Result.buildSuccess(results);
    }

}
