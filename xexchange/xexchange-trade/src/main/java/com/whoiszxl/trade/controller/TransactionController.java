package com.whoiszxl.trade.controller;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.trade.pojo.request.TransactionRequest;
import com.whoiszxl.trade.service.TransactionsService;
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
        Claims memberClaims = JwtUtils.getUserClaims(request);
        Long memberId = Long.parseLong(memberClaims.getId());
        transactionRequest.setMemberId(memberId);

        boolean flag = transactionsService.addOrder(transactionRequest);
        if(flag) {
            return Result.buildSuccess();
        }
        return Result.buildError();

    }

}
