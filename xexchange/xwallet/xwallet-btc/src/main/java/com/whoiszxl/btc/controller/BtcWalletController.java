package com.whoiszxl.btc.controller;

import com.whoiszxl.btc.service.BitcoinService;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.xwallet.core.base.BaseController;
import com.whoiszxl.xwallet.core.entity.common.FeignWithdrawRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: btc钱包控制器
 * @author: whoiszxl
 * @create: 2020-01-19
 **/
@RestController
@RequestMapping("/btc")
public class BtcWalletController implements BaseController {

    @Autowired
    private BitcoinService bitcoinService;

    @Override
    @PostMapping("/blockHeight")
    public Result blockHeight() {
        return Result.buildSuccess(bitcoinService.getBlockHeight());
    }

    @Override
    @PostMapping("/getNewAddress")
    public Result getNewAddress(String memberId) {
        return Result.buildSuccess(bitcoinService.getNewAddress(memberId));
    }

    @Override
    @PostMapping({"/withdraw", "/transfer"})
    public Result withdraw(@RequestBody FeignWithdrawRequest withdrawRequest) {
        String txId = bitcoinService.sendToAddress(withdrawRequest.getToAddress(), withdrawRequest.getAmount());
        return Result.buildSuccess(txId);
    }

    @Override
    public Result transfer(FeignWithdrawRequest withdrawRequest) {
        return null;
    }

    @Override
    @PostMapping("/getBalance")
    public Result getBalance() {
        return Result.buildSuccess(bitcoinService.getBalance());
    }
}
