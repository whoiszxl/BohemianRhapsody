package com.whoiszxl.wallet.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.jwt.JwtUtils;
import com.whoiszxl.base.utils.VoPoConverter;
import com.whoiszxl.wallet.base.pojo.ZxlUserAddress;
import com.whoiszxl.wallet.base.pojo.walletvo.BtcRequest;
import com.whoiszxl.wallet.pojo.vo.ZxlUserAddressVo;
import com.whoiszxl.wallet.service.AddressDispenseService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 地址分发代理控制器
 * @author: whoiszxl
 * @create: 2019-09-15
 **/
@RestController
@CrossOrigin
@RequestMapping("/wallet/address")
public class AddressDispenseController {

    @Autowired
    private AddressDispenseService addressDispenseService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/dispense")
    public Result<ZxlUserAddressVo> addressDispense(@RequestBody BtcRequest btcRequest) {
        Claims userClaims = JwtUtils.getUserClaims(request);
        String userId = userClaims.getId();
        btcRequest.setUserId(userId);

        //获取结果并转Vo
        ZxlUserAddress zxlUserAddress = addressDispenseService.createOrGetAddress(btcRequest);
        ZxlUserAddressVo zxlUserAddressVo = VoPoConverter.copyProperties(zxlUserAddress, ZxlUserAddressVo.class);
        return Result.buildSuccess(zxlUserAddressVo);
    }

}
