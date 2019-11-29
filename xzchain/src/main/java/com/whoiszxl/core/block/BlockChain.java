package com.whoiszxl.core.block;

import com.whoiszxl.core.transaction.SpendableOutputResult;
import com.whoiszxl.core.transaction.TXInput;
import com.whoiszxl.core.transaction.TXOutput;
import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.utils.ByteUtils;
import com.whoiszxl.utils.RocksDBUtils;
import lombok.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 区块链类
 * @author: whoiszxl
 * @create: 2019-11-12
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlockChain {

    /**
     * 最后一个区块的Hash
     */
    private String lastBlockHash;

    /**
     * 创建区块链,获取最新区块hash，不存在则创建区块链，存在则直接创建BlockChain对象
     * @return
     */
    public static BlockChain getOrCreateBlockChain(String address) {
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        if(StringUtils.isBlank(lastBlockHash)) {
            //数据不存在说明为第一次获取区块链实例
            Transaction coinbaseTx = Transaction.newCoinbaseTx(address, "");
            //创建创世区块，并添加coinbase奖励
            Block genesisBlock = Block.createNewGenesisBlock(coinbaseTx);
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
     * 打包交易，进行挖矿
     * @param transactions
     * @throws Exception
     */
    public void mineBlock(List<Transaction> transactions) throws Exception {
        String lastBlockHash = RocksDBUtils.getInstance().getLastBlockHash();
        Block lastBlock = RocksDBUtils.getInstance().getBlock(lastBlockHash);
        if (lastBlockHash == null) {
            throw new Exception("ERROR: Fail to get last block hash ! ");
        }
        Block newBlock = Block.createNewBlock(lastBlockHash, transactions, lastBlock.getHeight() + 1);
        this.addBlock(newBlock);
    }


    /**
     * 从交易输入中查询区块链中所有已经被花费了的交易输出
     * @param address 钱包地址
     * @return
     */
    private Map<String, Integer[]> getAllSpentTXOs(String address) {
        // 定义TxId ——> spentOutIndex[]，存储交易ID与已被花费的交易输出数组索引值
        Map<String, Integer[]> spentTXOs = new HashMap<>();
        //遍历所有区块
        for (BlockChainIterator blockChainIterator = this.getBlockchainIterator(); blockChainIterator.hashNext(); ) {
            Block block = blockChainIterator.next();
            //从区块中拿到所有交易进行遍历
            for (Transaction transaction : block.getTransactions()) {
                // 如果是 coinbase 交易，直接跳过，因为它不存在引用前一个区块的交易输出
                if (transaction.isCoinbase()) {
                    continue;
                }
                //遍历inputs,获取传入地址的
                for (TXInput txInput : transaction.getInputs()) {
                    //拿到address这个地址下所有的input
                    if(txInput.canUnlockOutputWith(address)) {
                        //将txId对应其中已被花费的output的索引进行关联
                        String inTxId = Hex.encodeHexString(txInput.getTxId());
                        Integer[] spentOutIndexArray = spentTXOs.get(inTxId);
                        if (spentOutIndexArray == null) {
                            spentTXOs.put(inTxId, new Integer[]{txInput.getTxOutputIndex()});
                        } else {
                            spentOutIndexArray = ArrayUtils.add(spentOutIndexArray, txInput.getTxOutputIndex());
                            spentTXOs.put(inTxId, spentOutIndexArray);
                        }
                    }
                }

            }
        }
        return spentTXOs;
    }


    /**
     * 查找钱包地址对应的所有未花费的交易
     *
     * @param address 钱包地址
     * @return 所有此地址可解锁的，并且是含有未花费交易输出的交易集合
     */
    private Transaction[] findUnspentTransactions(String address) {
        Map<String, Integer[]> allSpentTXOs = this.getAllSpentTXOs(address);
        Transaction[] unspentTxs = {};

        //遍历所有区块中的交易输出
        for (BlockChainIterator blockchainIterator = this.getBlockchainIterator(); blockchainIterator.hashNext(); ) {
            Block block = blockchainIterator.next();
            //遍历区块中的所有交易
            for (Transaction transaction : block.getTransactions()) {
                //获取当前遍历交易的hash
                String txId = Hex.encodeHexString(transaction.getTxId());
                //获取当前交易已经花费了的output下标数组
                Integer[] spentOutIndexArray = allSpentTXOs.get(txId);
                //判断当前交易的没一个下标是否在已花费了的数组里，不存在则判断是否能解锁，能解锁则将此笔交易存入结果返回
                for (int outIndex = 0; outIndex < transaction.getOutputs().length; outIndex++) {
                    if (spentOutIndexArray != null && ArrayUtils.contains(spentOutIndexArray, outIndex)) {
                        continue;
                    }

                    // 保存不存在 allSpentTXOs 中的交易
                    if (transaction.getOutputs()[outIndex].canBeUnlockedWith(address)) {
                        unspentTxs = ArrayUtils.add(unspentTxs, transaction);
                    }
                }
            }
        }
        return unspentTxs;
    }

    /**
     * 查找钱包地址对应的所有UTXO
     *
     * @param address 钱包地址
     * @return
     */
    public TXOutput[] findUTXO(String address) throws Exception {
        //拿到这个地址下所有含有UTXO的交易集合
        Transaction[] unspentTxs = this.findUnspentTransactions(address);
        TXOutput[] utxos = {};
        if (unspentTxs == null || unspentTxs.length == 0) {
            return utxos;
        }
        //遍历所有交易下的output，将可以解锁的添加到结果中返回
        for (Transaction tx : unspentTxs) {
            for (TXOutput txOutput : tx.getOutputs()) {
                if (txOutput.canBeUnlockedWith(address)) {
                    utxos = ArrayUtils.add(utxos, txOutput);
                }
            }
        }
        return utxos;
    }

    /**
     * 寻找能够花费的交易
     *
     * @param address 钱包地址
     * @param amount  花费金额
     * @return 返回交易时的实际支付金额和使用到的交易对应的output下标
     */
    public SpendableOutputResult findSpendableOutputs(String address, Integer amount) throws Exception {
        //获取到所有utxo
        Transaction[] unspentTXs = this.findUnspentTransactions(address);
        Integer accumulated = 0;
        Map<String, Integer[]> unspentOuts = new HashMap<>();
        //遍历所有utxo
        for (Transaction tx : unspentTXs) {

            //拿到遍历中的utxo的txHash
            String txId = Hex.encodeHexString(tx.getTxId());

            //遍历所有的output
            for (Integer outId = 0; outId < tx.getOutputs().length; outId++) {

                TXOutput txOutput = tx.getOutputs()[outId];

                //判断当前的这个utxo是否是当前address的， 并对需要的金额进行累计
                if (txOutput.canBeUnlockedWith(address) && accumulated < amount) {
                    //将output中可用的金额进行累加
                    accumulated += txOutput.getValue();
                    //将这个outId的索引添加到unspentOuts中去
                    Integer[] outIds = unspentOuts.get(txId);
                    if (outIds == null) {
                        outIds = new Integer[]{outId};
                    } else {
                        outIds = ArrayUtils.add(outIds, outId);
                    }
                    unspentOuts.put(txId, outIds);
                    if (accumulated >= amount) {
                        break;
                    }
                }
            }
        }
        return new SpendableOutputResult(accumulated, unspentOuts);
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
