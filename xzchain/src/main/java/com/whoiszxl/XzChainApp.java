package com.whoiszxl;

import com.whoiszxl.core.block.Block;
import com.whoiszxl.core.block.BlockChain;
import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.pow.ProofOfWork;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 启动类
 * @author: whoiszxl
 * @create: 2019-11-12
 **/
public class XzChainApp {

    public static void main(String[] args) {

        BlockChain blockChain = BlockChain.newBlockChain();
        System.out.println("创世链的信息：");
        System.out.println("区块的长度：" + blockChain.getBlockList().size());

        //4.添加区块
        List<Transaction> transaction1 = new ArrayList<>();
        List<Transaction> transaction2 = new ArrayList<>();
        List<Transaction> transaction3 = new ArrayList<>();
        blockChain.addBlock(transaction1);
        blockChain.addBlock(transaction2);
        blockChain.addBlock(transaction3);

        for (int i = 0; i < blockChain.getBlockList().size(); i++) {
            Block block = blockChain.getBlockList().get(i);
            System.out.println("第"+block.getHeight()+"个区块信息：");
            System.out.println("\tprevBlockHash: " + block.getPreviousHash());
            System.out.println("\tData: " + block.getTransactions());
            System.out.println("\tHash: " + block.getHash());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date2 = sdf.format(new Date(block.getTimestamp()*1000L));
            System.out.println("\ttimeStamp:" + date2);
            System.out.println();

            ProofOfWork pow = ProofOfWork.newProofOfWork(block);
            System.out.println("是否有效: " + pow.validate() + "\n");
            System.out.println();
        }
    }
}
