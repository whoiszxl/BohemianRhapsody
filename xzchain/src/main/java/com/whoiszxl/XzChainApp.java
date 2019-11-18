package com.whoiszxl;

import com.whoiszxl.core.block.Block;
import com.whoiszxl.core.block.BlockChain;
import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.utils.RocksDBUtils;

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

        //4.添加区块
        List<Transaction> transaction1 = new ArrayList<>();
        List<Transaction> transaction2 = new ArrayList<>();
        List<Transaction> transaction3 = new ArrayList<>();

        BlockChain blockchain = BlockChain.newBlockChain();
        System.out.println(blockchain);

        try {
            blockchain.addBlock(transaction1);
            blockchain.addBlock(transaction2);
            blockchain.addBlock(transaction3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RocksDBUtils.getInstance().closeDB();
        }

        BlockChain.BlockChainIterator iterator = blockchain.getBlockchainIterator();
        long index = 0;
        while (iterator.hashNext()) {
            Block block = iterator.next();
            System.out.println("第" + block.getHeight() + "个区块信息：");
            System.out.println("\tprevBlockHash: " + block.getPreviousHash());
            System.out.println("\tData: " + block.getTransactions());
            System.out.println("\tHash: " + block.getHash());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date(block.getTimestamp() * 1000L));
            System.out.println("\ttimeStamp:" + date);
            System.out.println();
        }
        RocksDBUtils.getInstance().closeDB();

    }
}
