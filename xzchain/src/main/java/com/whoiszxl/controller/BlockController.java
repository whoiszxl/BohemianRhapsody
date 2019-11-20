package com.whoiszxl.controller;

import com.whoiszxl.core.block.Block;
import com.whoiszxl.core.block.BlockChain;
import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.entity.Result;
import com.whoiszxl.pow.ProofOfWork;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 区块控制器
 * @author: whoiszxl
 * @create: 2019-11-19
 **/
@RestController
@RequestMapping("/block")
public class BlockController {


    /**
     * 创建区块链
     * @return 结果
     */
    @PostMapping("/createBlockChain")
    public Result createBlockChainWithGenesisBlock() {
        BlockChain blockChain = BlockChain.newBlockChain();
        return Result.buildSuccess();
    }


    /**
     * 添加区块
     *
     */
    @PostMapping("/addBlock")
    public Result addBlock() {
        BlockChain blockchain = BlockChain.newBlockChain();
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
    public Result printChain() {
        BlockChain blockchain = BlockChain.newBlockChain();
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
