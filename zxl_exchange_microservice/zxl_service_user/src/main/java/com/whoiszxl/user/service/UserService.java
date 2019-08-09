package com.whoiszxl.user.service;

import com.whoiszxl.base.service.BaseService;
import com.whoiszxl.user.pojo.ZxlUser;
import com.whoiszxl.user.pojo.request.RegisterRequest;

import java.util.Map;

/**
 * @description: 用户服务接口
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
public interface UserService extends BaseService<ZxlUser> {

    /**
     * 校验当前手机输入的验证码是否正确
     * @param mobile 用户手机号
     * @param userVerifyCode 用户输入的验证码
     * @return
     */
    boolean checkVerifyCode(String mobile, String userVerifyCode);

    boolean registerToDb(RegisterRequest registerRequest);

    ZxlUser login(String phone, String password);

    Map<String,Object> issueSign(ZxlUser zxlUser, String roleName);
}
