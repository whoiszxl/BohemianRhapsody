package com.whoiszxl.eth.controller;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.xwallet.core.base.BaseController;
import com.whoiszxl.xwallet.core.entity.Coin;
import com.whoiszxl.xwallet.core.entity.common.FeignGetAddressRequest;
import com.whoiszxl.xwallet.core.entity.common.FeignWithdrawRequest;
import com.whoiszxl.xwallet.core.service.AccountService;
import com.whoiszxl.xwallet.ethereum.core.service.EthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-20
 **/
@Slf4j
@RestController
@RequestMapping("/eth")
public class EthWalletController implements BaseController {

    @Autowired
    private EthService ethService;
    @Autowired
    private Web3j web3j;
    @Autowired
    private EthWatcher watcher;
    @Autowired
    private Coin coin;
    @Autowired
    private AccountService accountService;

    @Override
    @PostMapping("/blockHeight")
    public Result blockHeight() {
        try {
            EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
            long rpcBlockNumber = ethBlockNumber.getBlockNumber().longValue();
            return Result.buildSuccess(rpcBlockNumber);
        } catch (IOException e) {
            log.error("获取ETH区块高度失败:", e);
            return Result.buildError("获取ETH区块高度失败");
        }

    }

    @Override
    @PostMapping("/getNewAddress")
    public Result getNewAddress(@RequestBody FeignGetAddressRequest feignGetAddressRequest) {
        try {
            String newWallet = ethService.createNewWallet(feignGetAddressRequest.getMemberId().toString(), feignGetAddressRequest.getPassword());
            return Result.buildSuccess(newWallet);
        } catch (Exception e) {
            log.error("创建ETH地址失败:", e);
            return Result.buildError("创建ETH地址失败");
        }
    }

    @Override
    @PostMapping("/withdraw")
    public Result withdraw(@RequestBody FeignWithdrawRequest withdrawRequest) {
        return ethService.transferFromWithdrawWallet(withdrawRequest.getToAddress(), withdrawRequest.getAmount(), true, withdrawRequest.getWithdrawId().toString());
    }

    @Override
    @PostMapping("/transfer")
    public Result transfer(FeignWithdrawRequest withdrawRequest) {
        return null;
    }

    @Override
    @PostMapping("/getBalance")
    public Result getBalance() {
        return null;
    }

    @PostMapping("/getBalance")
    public Result getBalance() {

    }
}
