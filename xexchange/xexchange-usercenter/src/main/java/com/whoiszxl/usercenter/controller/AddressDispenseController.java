package com.whoiszxl.usercenter.controller;

import com.whoiszxl.core.entity.MemberAddress;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.core.utils.VoPoConverter;
import com.whoiszxl.usercenter.entity.vo.MemberAddressVo;
import com.whoiszxl.usercenter.service.AddressDispenseService;
import com.whoiszxl.xwallet.core.entity.btc.WalletRequest;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 地址分发代理控制器
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressDispenseController {

    @Autowired
    private AddressDispenseService addressDispenseService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/dispense")
    public Result<MemberAddressVo> addressDispense(@RequestBody WalletRequest walletRequest) {
        Claims userClaims = JwtUtils.getUserClaims(request);
        Long memberId = Long.parseLong(userClaims.getId());
        walletRequest.setMemberId(memberId);

        //获取结果并转Vo
        MemberAddress memberAddress = addressDispenseService.createOrGetAddress(walletRequest);
        MemberAddressVo memberAddressVo = VoPoConverter.copyProperties(memberAddress, MemberAddressVo.class);
        return Result.buildSuccess(memberAddressVo);
    }
}
