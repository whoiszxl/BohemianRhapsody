package com.whoiszxl;

import com.whoiszxl.block.Block;
import com.whoiszxl.block.BlockChain;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        blockChain.addBlock("Send 1 BTC to xz");
        blockChain.addBlock("Send 2 more BTC to zxl");
        blockChain.addBlock("Send 4 more BTC to phq");

        for (int i = 0; i < blockChain.getBlockList().size(); i++) {
            Block block = blockChain.getBlockList().get(i);
            System.out.println("第"+block.getHeight()+"个区块信息：");
            System.out.println("\tprevBlockHash: " + block.getPreviousHash());
            System.out.println("\tData: " + block.getData());
            System.out.println("\tHash: " + block.getHash());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date2 = sdf.format(new Date(block.getTimestamp()*1000L));
            System.out.println("\ttimeStamp:" + date2);
            System.out.println();
        }
    }
}
