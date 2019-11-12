package com.whoiszxl.block;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 区块链类
 * @author: whoiszxl
 * @create: 2019-11-12
 **/
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlockChain {

    /**
     * 区块集合
     */
    private List<Block> blockList;

    /**
     * 创建区块链
     * @return
     */
    public static BlockChain newBlockChain() {
        List<Block> blocks = new LinkedList<>();
        blocks.add(Block.createNewGenesisBlock());
        return new BlockChain(blocks);
    }

    /**
     * 通过block添加区块
     * @param block
     */
    public void addBlock(Block block) {
        this.blockList.add(block);
    }

    /**
     * 通过transaction添加区块
     * @param data
     */
    public void addBlock(String data) {
        Block previousBlock = blockList.get(blockList.size() - 1);
        this.addBlock(Block.createNewBlock(previousBlock.getHash(), data,previousBlock.getHeight() + 1));
    }
}
