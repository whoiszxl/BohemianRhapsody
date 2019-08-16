package com.whoiszxl.wallet.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.wallet.pojo.ZxlCurrency;
import com.whoiszxl.wallet.pojo.request.PageRequest;
import com.whoiszxl.wallet.service.ZxlCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户控制器
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@RestController
@CrossOrigin
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private ZxlCurrencyService zxlCurrencyService;


    @PostMapping("/currencyList")
    public Result currencyList(@RequestBody PageRequest pageRequest){
        //获取币种列表
        List<ZxlCurrency> zxlCurrencies = zxlCurrencyService.queryPageByStatus(pageRequest);
        return Result.buildSuccess(zxlCurrencies);
    }
}
