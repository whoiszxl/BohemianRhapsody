package com.whoiszxl.core.block;

import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.utils.ByteUtils;
import com.whoiszxl.utils.RocksDBUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

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
     * 最后一个区块的Hash
     */
    private String lastBlockHash;

    /**
     * 创建区块链
     * @return
     */
    public static BlockChain newBlockChain() {
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        if(StringUtils.isBlank(lastBlockHash)) {
            Block genesisBlock = Block.createNewGenesisBlock();
            lastBlockHash = genesisBlock.getHash();
            RocksDBUtils.getInstance().putBlock(genesisBlock);
            RocksDBUtils.getInstance().putLastBlockHash(lastBlockHash);
        }

        return new BlockChain(lastBlockHash);
    }

    /**
     * 通过block添加区块
     * @param block 区块
     */
    public void addBlock(Block block) {
        RocksDBUtils.getInstance().putLastBlockHash(block.getHash());
        RocksDBUtils.getInstance().putBlock(block);
        this.lastBlockHash = block.getHash();
    }

    /**
     * 通过transaction添加区块
     * @param transactions 区块数据
     */
    public void addBlock(List<Transaction> transactions) throws Exception {
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        Block lastBlock = RocksDBUtils.getInstance().getBlock(lastBlockHash);
        if (StringUtils.isBlank(lastBlockHash)){
            throw new Exception("还没有数据库，无法直接添加区块。。");
        }
        this.addBlock(Block.createNewBlock(lastBlockHash, transactions, lastBlock.getHeight() + 1));
    }


    /**
     * 区块链迭代器
     */
    public class BlockChainIterator {

        /**
         * 当前区块的hash
         */
        private String currentBlockHash;


        /**
         * 构造函数，构建迭代器
         * @param currentBlockHash 当前区块的hash
         */
        public BlockChainIterator(String currentBlockHash) {
            this.currentBlockHash = currentBlockHash;
        }

        /**
         * 判断是否有下一个区块
         * @return
         */
        public boolean hashNext() {
            //有效性判断
            if(ByteUtils.ZERO_HASH.equals(currentBlockHash)) {
                return false;
            }
            Block lastBlock = RocksDBUtils.getInstance().getBlock(currentBlockHash);
            if(lastBlock == null) {
                return false;
            }

            //上一个区块hash为0，则其具有下一个区块
            if(ByteUtils.ZERO_HASH.equals(lastBlock.getPreviousHash())) {
                return true;
            }

            return RocksDBUtils.getInstance().getBlock(lastBlock.getPreviousHash()) != null;
        }

        /**
         * 迭代获取区块
         * @return
         */
        public Block next() {
            Block currentBlock = RocksDBUtils.getInstance().getBlock(currentBlockHash);
            if (currentBlock != null) {
                this.currentBlockHash = currentBlock.getPreviousHash();
                return currentBlock;
            }
            return null;
        }

    }

    /**
     * 添加方法，用于获取迭代器实例
     * @return
     */
    public BlockChainIterator getBlockchainIterator() {
        return new BlockChainIterator(lastBlockHash);
    }

}
