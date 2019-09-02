package com.whoiszxl.btc.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.btc.service.BitcoinService;
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
    public Result<String> getNewAddress(@RequestBody Map params) {
        String userId = (String) params.get("userId");
        String newAddress = bitcoinService.getNewAddress(userId);
        if(StringUtils.isNotBlank(newAddress)) {
            return Result.buildSuccess(newAddress);
        }
        return Result.buildError();
    }


    @PostMapping("/getBalance")
    public Result<String> getBalance() {
        String balance = bitcoinService.getBalance();
        if(StringUtils.isNotBlank(balance)) {
            return Result.buildSuccess(balance);
        }
        return Result.buildError();
    }
}
