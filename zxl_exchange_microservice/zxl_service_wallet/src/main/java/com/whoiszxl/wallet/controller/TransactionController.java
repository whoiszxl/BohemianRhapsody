package com.whoiszxl.wallet.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.jwt.JwtUtils;
import com.whoiszxl.wallet.service.TransactionsService;
import com.whoiszxl.wallet.pojo.request.TransactionRequest;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 挂单操作控制器
 * @author: whoiszxl
 * @create: 2019-10-31
 **/
@RestController
@CrossOrigin
@RequestMapping("/wallet/transaction")
public class TransactionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/addTransaction")
    public Result addTransaction(@RequestBody TransactionRequest transactionRequest) {
        Claims userClaims = JwtUtils.getUserClaims(request);
        String userId = userClaims.getId();
        transactionRequest.setUserId(userId);

        boolean flag = transactionsService.addOrder(transactionRequest);
        if(flag) {
            return Result.buildSuccess();
        }
        return Result.buildError();

    }

}
