package com.whoiszxl.core.block;

import com.whoiszxl.constant.Constants;
import com.whoiszxl.core.transaction.Transaction;
import com.whoiszxl.pow.PowResult;
import com.whoiszxl.pow.ProofOfWork;
import com.whoiszxl.utils.ByteUtils;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 区块类
 * @author: whoiszxl
 * @create: 2019-11-12
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Block implements Serializable {

    /**
     * 区块版本，规定了区块遵守的验证规则
     * https://bitcoin.org/en/developer-reference#block-versions
     */
    private Integer version;

    /**
     * 当前区块的Hash值
     */
    private String hash;

    /**
     * 上一个区块的 hash 地址
     */
    private String previousHash;

    /**
     * 区块数据
     */
    private List<Transaction> transactions;

    /**
     * 时间戳
     */
    private Long timestamp;


    /**
     * 符合POW答案规则的随机数
     */
    private Long nonce;

    /**
     * 工作量证明POW的难度
     */
    private BigInteger difficulty;

    /**
     * 区块高度
     */
    private Integer height;


    /**
     * 创建一个新的区块
     * @return 新区块
     */
    public static Block createNewBlock(String previousHash, List<Transaction> transactions, Integer height) {
        Block block = Block.builder().version(Constants.XZ_VERSION).previousHash(previousHash).transactions(transactions).height(height).timestamp(System.currentTimeMillis()).build();
        ProofOfWork pow = ProofOfWork.newProofOfWork(block);
        PowResult powResult = pow.run();
        block.setHash(powResult.getHash());
        block.setNonce(powResult.getNonce());
        return block;
    }


    /**
     * 创建创世区块
     * @return
     */
    public static Block createNewGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        return Block.createNewBlock(Constants.ZERO_HASH, transactions, 0);
    }


    /**
     * 设置区块hash，将上一个区块hash并上当前区块数据和时间戳进行sha256加密获得
     */
//    public void setBlockHash() {
//        byte[] prevBlockHashBytes = {};
//        if(StringUtils.isNotBlank(this.getPreviousHash())) {
//            prevBlockHashBytes = new BigInteger(this.getPreviousHash(), 16).toByteArray();
//        }
//
//        byte[] headers = ByteUtils.merge(prevBlockHashBytes, this.getTransactions().toString().getBytes(), ByteUtils.toBytes(this.getTimestamp()));
//
//        this.setHash(DigestUtils.sha256Hex(headers));
//    }


    /**
     * 对区块中的交易信息进行hash计算
     * @return hash计算后的区块交易信息
     */
    public byte[] hashTransaction() {
        byte[][] txIdArrays = new byte[this.getTransactions().size()][];
        for (int i = 0; i < this.getTransactions().size(); i++) {
            txIdArrays[i] = this.getTransactions().get(i).getTxId();
        }
        return DigestUtils.sha256(ByteUtils.merge(txIdArrays));
    }
}
