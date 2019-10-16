package com.whoiszxl.wallet.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.entity.ZxlPageRequest;
import com.whoiszxl.base.jwt.JwtUtils;
import com.whoiszxl.base.utils.VoPoConverter;
import com.whoiszxl.wallet.base.pojo.ZxlCurrency;
import com.whoiszxl.wallet.base.pojo.ZxlUserRecharge;
import com.whoiszxl.wallet.base.pojo.ZxlUserWithdraw;
import com.whoiszxl.wallet.base.service.ZxlCurrencyService;
import com.whoiszxl.wallet.pojo.request.WalletRequest;
import com.whoiszxl.wallet.pojo.vo.ZxlCurrencyVo;
import com.whoiszxl.wallet.service.RechargeWithdrawService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户控制器
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@RestController
@CrossOrigin
@RequestMapping("/wallet/asset")
public class WalletController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ZxlCurrencyService zxlCurrencyService;

    @Autowired
    private RechargeWithdrawService rechargeWithdrawService;


    @PostMapping("/currencyList")
    public Result<List<ZxlCurrencyVo>> currencyList(@RequestBody ZxlPageRequest pageRequest){
        //获取币种列表
        List<ZxlCurrency> zxlCurrencies = zxlCurrencyService.queryPageByStatus(pageRequest);
        List<ZxlCurrencyVo> zxlCurrencyVos = VoPoConverter.copyList(zxlCurrencies, ZxlCurrencyVo.class);
        return Result.buildSuccess(zxlCurrencyVos);
    }

    @PostMapping("/rechargeList")
    public Result<List<ZxlUserRecharge>> rechargeList(@RequestBody WalletRequest walletRequest) {
        String userId = JwtUtils.getUserClaims(request).getId();
        Integer currencyId = Integer.parseInt(walletRequest.getCurrencyId());
        List<ZxlUserRecharge> rechargeList = rechargeWithdrawService.getRechargeListByUserIdAndCurrencyId(userId, currencyId);
        return Result.buildSuccess(rechargeList);
    }

    @PostMapping("/withdrawList")
    public Result<List<ZxlUserWithdraw>> withdrawList(@RequestBody WalletRequest walletRequest) {
        String userId = JwtUtils.getUserClaims(request).getId();
        Integer currencyId = Integer.parseInt(walletRequest.getCurrencyId());
        List<ZxlUserWithdraw> withdrawList = rechargeWithdrawService.getWithdrawListByUserIdAndCurrencyId(userId, currencyId);
        return Result.buildSuccess(withdrawList);
    }


    @PostMapping("/assetList")
    public Result assetList() {
        Claims userClaims = JwtUtils.getUserClaims(request);
        String userId = userClaims.getId();
        List assetList = zxlCurrencyService.getAssetList(userId);
        return Result.buildSuccess(assetList);
    }


    @PostMapping("/getAssetByCurrencyId")
    public Result getAssetByCurrencyId(@RequestBody WalletRequest walletRequest) {
        Claims userClaims = JwtUtils.getUserClaims(request);
        String userId = userClaims.getId();
        List assetList = zxlCurrencyService.getAssetList(userId);
        for (Object o : assetList) {
            Map map = (Map)o;
            if(StringUtils.equals(map.get("id").toString(), walletRequest.getCurrencyId())) {
                return Result.buildSuccess(map);
            }
        }
        return Result.buildError();
    }
}
