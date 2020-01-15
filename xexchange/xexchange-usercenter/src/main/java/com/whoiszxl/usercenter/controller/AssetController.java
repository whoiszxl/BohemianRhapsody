package com.whoiszxl.usercenter.controller;

import com.whoiszxl.core.entity.MemberRechargeRecord;
import com.whoiszxl.core.entity.MemberWithdraw;
import com.whoiszxl.core.entity.TradeCoin;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.entity.base.ZxlPageRequest;
import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.core.utils.VoPoConverter;
import com.whoiszxl.usercenter.entity.vo.CoinVo;
import com.whoiszxl.usercenter.service.MemberWithdrawRecordService;
import com.whoiszxl.usercenter.service.TradeCoinService;
import com.whoiszxl.xwallet.core.entity.btc.WalletRequest;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 资产相关控制器
 * @author: whoiszxl
 * @create: 2020-01-15
 **/
@RestController
@CrossOrigin
@RequestMapping("/asset")
public class AssetController {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TradeCoinService tradeCoinService;

    @Autowired
    private MemberWithdrawRecordService memberWithdrawRecordService;


    @PostMapping("/coinList")
    public Result<List<CoinVo>> coinList(@RequestBody ZxlPageRequest pageRequest){
        //获取币种列表
        List<TradeCoin> tradeCoins = tradeCoinService.queryPageByStatus(pageRequest);
        List<CoinVo> zxlCurrencyVos = VoPoConverter.copyList(tradeCoins, CoinVo.class);
        return Result.buildSuccess(zxlCurrencyVos);
    }

    @PostMapping("/rechargeList")
    public Result<List<MemberRechargeRecord>> rechargeList(@RequestBody WalletRequest walletRequest) {
        Long memberId = Long.parseLong(JwtUtils.getUserClaims(request).getId());
        Long coinId = walletRequest.getCoinId();
        List<MemberRechargeRecord> rechargeList = memberWithdrawRecordService.getRechargeListByMemberIdAndCoinId(memberId, coinId);
        return Result.buildSuccess(rechargeList);
    }

    @PostMapping("/withdrawList")
    public Result<List<MemberWithdraw>> withdrawList(@RequestBody WalletRequest walletRequest) {
        Long memberId = Long.parseLong(JwtUtils.getUserClaims(request).getId());
        Long coinId = walletRequest.getCoinId();
        List<MemberWithdraw> withdrawList = memberWithdrawRecordService.getWithdrawListByMemberIdAndCoinId(memberId, coinId);
        return Result.buildSuccess(withdrawList);
    }


    @PostMapping("/assetList")
    public Result assetList() {
        Long memberId = Long.parseLong(JwtUtils.getUserClaims(request).getId());
        List assetList = tradeCoinService.getAssetList(memberId);
        return Result.buildSuccess(assetList);
    }


    @PostMapping("/getAssetByCurrencyId")
    public Result getAssetByCurrencyId(@RequestBody WalletRequest walletRequest) {
        Long memberId = Long.parseLong(JwtUtils.getUserClaims(request).getId());
        List assetList = tradeCoinService.getAssetList(memberId);
        for (Object o : assetList) {
            Map map = (Map)o;
            if(StringUtils.equals(map.get("id").toString(), walletRequest.getCoinId().toString())) {
                return Result.buildSuccess(map);
            }
        }
        return Result.buildError();
    }
}
