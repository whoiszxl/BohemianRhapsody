package com.whoiszxl.btc.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.btc.pojo.TransactionInfoResponse;
import com.whoiszxl.btc.service.BitcoinService;
import com.whoiszxl.wallet.base.pojo.walletvo.BtcRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2019-08-31
 **/
@RestController
@RequestMapping("/btc")
public class WalletController {

    @Autowired
    private BitcoinService bitcoinService;


    @PostMapping("/getNewAddress")
    public String getNewAddress(@RequestBody BtcRequest btcRequest) {
        return bitcoinService.getNewAddress(btcRequest.getUserId());
    }


    @PostMapping("/getBalance")
    public String getBalance() {
        return bitcoinService.getBalance();
    }

    @PostMapping("/getTransaction")
    public TransactionInfoResponse getTransaction(@RequestBody BtcRequest btcRequest) {
        return bitcoinService.getTransaction(btcRequest.getTxHash());
    }
}
