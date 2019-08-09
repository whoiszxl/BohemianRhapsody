package com.whoiszxl.user.controller;

import com.whoiszxl.base.entity.Result;
import com.whoiszxl.base.entity.StatusCode;
import com.whoiszxl.base.enums.role.UserRoleEnum;
import com.whoiszxl.base.pojo.BasePojo;
import com.whoiszxl.base.utils.ValidateUtils;
import com.whoiszxl.user.pojo.ZxlUser;
import com.whoiszxl.user.pojo.request.RegisterRequest;
import com.whoiszxl.user.pojo.request.SmsRequest;
import com.whoiszxl.user.service.UserService;
import com.whoiszxl.user.sms.AliyunSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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


    /**
     * 发送短信接口
     * @param smsRequest mobile 手机号
     * @return
     */
    @PostMapping("/sendsms")
    public Result sendSms(@RequestBody SmsRequest smsRequest) {
        Result result = aliyunSmsSender.sendSms(smsRequest.getMobile());
        return result;
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody RegisterRequest registerRequest){
        //校验手机号的密码
        ValidateUtils.checkPasswordEqual(registerRequest.getPassword(), registerRequest.getRePassword());
        ValidateUtils.checkPasswordLevel(registerRequest.getPassword());
        ValidateUtils.checkPhoneRegex(registerRequest.getMobile());

        //校验缓存中的验证码
        boolean isSuccess = userService.checkVerifyCode(registerRequest.getMobile(), registerRequest.getCode());
        if(!isSuccess) {
            return Result.buildError("验证码输入错误");
        }

        //入库
        isSuccess = userService.registerToDb(registerRequest);
        if(!isSuccess) {
            return Result.buildError("注册失败");
        }
        return Result.buildSuccess("注册成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody ZxlUser zxlUser){
        //校验手机号的密码
        ValidateUtils.checkPasswordLevel(zxlUser.getPassword());
        ValidateUtils.checkPhoneRegex(zxlUser.getPhone());

        zxlUser = userService.login(zxlUser.getPhone(), zxlUser.getPassword());
        if(zxlUser == null) {
            return Result.buildError(StatusCode.LOGINERROR, "登录失败");
        }
        Map<String, Object> result = userService.issueSign(zxlUser, UserRoleEnum.ROLE_USER.getRoleName());
        return Result.buildSuccess(result);
    }
}
