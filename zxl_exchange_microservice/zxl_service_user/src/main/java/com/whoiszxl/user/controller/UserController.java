package com.whoiszxl.user.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.user.pojo.ZxlUser;
import com.whoiszxl.user.pojo.request.SmsRequest;
import com.whoiszxl.user.service.UserService;
import com.whoiszxl.user.sms.AliyunSmsSender;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AliyunSmsSender aliyunSmsSender;

    @GetMapping
    public Result<List<ZxlUser>> findAll() {
        return Result.buildSuccess(userService.findAll());
    }


    @PostMapping("/sendsms")
    public Result sendSms(@RequestBody SmsRequest smsRequest) {
        Result result = aliyunSmsSender.sendSms(smsRequest.getMobile());
        return result;
    }
}
