package com.whoiszxl.usercenter.service;

import com.whoiszxl.core.entity.Member;
import com.whoiszxl.usercenter.entity.request.RegisterRequest;
import com.whoiszxl.usercenter.entity.vo.MemberVo;

import java.util.Map;

/**
 * @description: 用户服务接口
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
public interface MemberService {

    /**
     * 校验当前手机输入的验证码是否正确
     * @param mobile 用户手机号
     * @param memberVerifyCode 用户输入的验证码
     * @return
     */
    boolean checkVerifyCode(String mobile, String memberVerifyCode);

    void registerToDb(RegisterRequest registerRequest);

    Member login(String phone, String password);

    Map<String,Object> issueSign(Member member, String roleName);

    void removeVerifyInRedis(String mobile);

    MemberVo getMemberInfoByMemberId(Long memberId);


}
