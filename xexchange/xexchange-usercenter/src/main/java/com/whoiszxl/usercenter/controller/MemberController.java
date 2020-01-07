package com.whoiszxl.usercenter.controller;

import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.entity.base.StatusCode;
import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.core.utils.ValidateUtils;
import com.whoiszxl.usercenter.entity.request.RegisterRequest;
import com.whoiszxl.usercenter.entity.request.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户控制器
 * @author: whoiszxl
 * @create: 2020-01-03
 **/
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private UserService userService;

    @Autowired
    private AliyunSmsSender aliyunSmsSender;

    @Autowired
    private CommonClient commonClient;

    @Autowired
    private HttpServletRequest request;

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

    @PostMapping("/info")
    public Result<ZxlUserVo> info() {
        String userId = JwtUtils.getUserClaims(request).getId();
        return Result.buildSuccess(userService.getUserInfoByUserId(userId));
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

        //入库并清除验证码
        userService.registerToDb(registerRequest);
        userService.removeVerifyInRedis(registerRequest.getMobile());

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
