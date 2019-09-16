package com.whoiszxl.wallet.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.entity.ZxlPageRequest;
import com.whoiszxl.base.utils.VoPoConverter;
import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.base.service.ZxlCurrencyService;
import com.whoiszxl.wallet.pojo.vo.ZxlCurrencyVo;
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
    public Result<List<ZxlCurrencyVo>> currencyList(@RequestBody ZxlPageRequest pageRequest){
        //获取币种列表
        List<ZxlCurrency> zxlCurrencies = zxlCurrencyService.queryPageByStatus(pageRequest);
        List<ZxlCurrencyVo> zxlCurrencyVos = VoPoConverter.copyList(zxlCurrencies, ZxlCurrencyVo.class);
        return Result.buildSuccess(zxlCurrencyVos);
    }


    public Result<String> createAddressByCurrencyId() {
        return null;
    }
}
