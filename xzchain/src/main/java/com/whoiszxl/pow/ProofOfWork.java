package com.whoiszxl.pow;

import com.whoiszxl.core.block.Block;
import com.whoiszxl.utils.ByteUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

/**
 * @description: POW工作量证明计算类
 * @author: whoiszxl
 * @create: 2019-11-15
 **/
@Data
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ProofOfWork {

    /**
     * 难度目标位
     * 0000 0000 0000 0000 1001 0001 0000  .... 0001
     * 256位Hash里面前面至少有16个零
     * 16个0的计算时间大约为20秒
     */
    public static final int TARGET_BITS = 16;

    /**
     * 工作量证明计算区块
     */
    private Block block;

    /**
     * 计算目标值
     */
    private BigInteger target;


    /**
     * 创建工作量证明对象
     * @param block
     * @return
     */
    public static ProofOfWork newProofOfWork(Block block) {

        //1向左位移6位，则：1000000

        BigInteger targetValue = BigInteger.ONE.shiftLeft((256 - TARGET_BITS));
        return ProofOfWork.builder().block(block).target(targetValue).build();
    }


    /**
     * 运行工作量证明计算，挖矿，找到小于难度目标值的hash
     * @return
     */
    public PowResult run() {
        long nonce = 0;
        String shaHex = "";
        log.info("开始进行挖矿，区块信息为：{}", this.getBlock());
        long startTime = System.currentTimeMillis();
        while (nonce < Long.MAX_VALUE) {
            byte[] data = this.prepareData(nonce);
            shaHex = DigestUtils.sha256Hex(data);
            log.info("pow答案 nonce：{}， 计算后的hash值：{}", nonce, shaHex);
            if(new BigInteger(shaHex, 16).compareTo(this.target) < 0) {
                log.info("挖矿成功，耗时：{}s", (float)(System.currentTimeMillis() - startTime) / 1000);
                log.info("当前区块的hash为：{}", shaHex);
                break;
            }else {
                nonce++;
            }
        }
        return new PowResult(nonce, shaHex);
    }

    /**
     * 根据block的数据，以及nonce生成一个字节数组
     * @param nonce pow答案
     * @return 区块字节数组
     */
    private byte[] prepareData(long nonce) {
        byte[] prevBlockHashBytes = {};
        if(StringUtils.isNotBlank(this.getBlock().getPreviousHash())) {
            prevBlockHashBytes = new BigInteger(this.getBlock().getPreviousHash(), 16).toByteArray();
        }

        return ByteUtils.merge(
                prevBlockHashBytes,
                this.getBlock().hashTransaction(),
                ByteUtils.toBytes(this.getBlock().getTimestamp()),
                ByteUtils.toBytes(TARGET_BITS),
                ByteUtils.toBytes(nonce));
    }

    /**
     * 验证区块是否有效
     * @return 是否有效
     */
    public boolean validate() {
        byte[] data = this.prepareData(this.getBlock().getNonce());
        return new BigInteger(DigestUtils.sha256Hex(data), 16).compareTo(this.target) < 0;
    }

}
