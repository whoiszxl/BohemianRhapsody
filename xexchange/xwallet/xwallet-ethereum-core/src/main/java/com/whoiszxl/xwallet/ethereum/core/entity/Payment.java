package com.whoiszxl.xwallet.ethereum.core.entity;

import lombok.Builder;
import lombok.Data;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@Data
@Builder
public class Payment {
    private String txBizNumber;
    private String txid;
    private Credentials credentials;
    private String to;
    private BigDecimal amount;
    private String unit;
    private BigInteger gasLimit;
    private BigInteger gasPrice;
}
