package com.whoiszxl.usercenter.controller;

import com.whoiszxl.core.entity.Member;
import com.whoiszxl.core.entity.base.Result;
import com.whoiszxl.core.entity.base.StatusCode;
import com.whoiszxl.core.enums.role.MemberRoleEnum;
import com.whoiszxl.core.exception.custom.JwtAuthException;
import com.whoiszxl.core.jwt.JwtUtils;
import com.whoiszxl.core.sms.SmsSender;
import com.whoiszxl.core.utils.ValidateUtils;
import com.whoiszxl.usercenter.entity.request.RegisterRequest;
import com.whoiszxl.usercenter.entity.request.SmsRequest;
import com.whoiszxl.usercenter.entity.vo.MemberVo;
import com.whoiszxl.usercenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    private MemberService memberService;

    @Autowired
    private SmsSender smsSender;


    @Autowired
    private HttpServletRequest request;

    /**
     * 发送短信接口
     * @param smsRequest mobile 手机号
     * @return
     */
    @PostMapping("/sendVerifySms")
    public Result sendVerifySms(@RequestBody SmsRequest smsRequest) {
        Result result = smsSender.sendVerifySms(smsRequest.getMobile());
        return result;
    }

    @PostMapping("/info")
    public Result<MemberVo> info() {
        String memberId = JwtUtils.getUserClaims(request).getId();
        return Result.buildSuccess(memberService.getMemberInfoByMemberId(Long.parseLong(memberId)));
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
        boolean isSuccess = memberService.checkVerifyCode(registerRequest.getMobile(), registerRequest.getCode());
        if(!isSuccess) {
            throw new JwtAuthException();
            //return Result.buildError("验证码输入错误");
        }

        //入库并清除验证码
        memberService.registerToDb(registerRequest);
        memberService.removeVerifyInRedis(registerRequest.getMobile());

        return Result.buildSuccess("注册成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Member member){
        //校验手机号的密码
        ValidateUtils.checkPasswordLevel(member.getPassword());
        ValidateUtils.checkPhoneRegex(member.getPhone());

        Member memberResult = memberService.login(member.getPhone(), member.getPassword());
        if(memberResult == null) {
            return Result.buildError(StatusCode.LOGINERROR, "登录失败");
        }
        Map<String, Object> result = memberService.issueSign(memberResult, MemberRoleEnum.ROLE_USER.getRoleName());
        return Result.buildSuccess(result);
    }
}
