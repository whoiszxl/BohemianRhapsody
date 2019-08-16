package com.whoiszxl.user.service.impl;

import com.google.common.collect.Maps;
import com.whoiszxl.base.enums.redis.UserRedisPrefixEnum;
import com.whoiszxl.base.enums.role.UserRoleEnum;
import com.whoiszxl.base.enums.user.UserStatusEnum;
import com.whoiszxl.base.jwt.JwtUtils;
import com.whoiszxl.base.utils.IdWorker;
import com.whoiszxl.base.utils.RedisUtils;
import com.whoiszxl.user.dao.UserDao;
import com.whoiszxl.user.pojo.ZxlUser;
import com.whoiszxl.user.pojo.request.RegisterRequest;
import com.whoiszxl.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @description: 用户服务实现类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkVerifyCode(String mobile, String userVerifyCode) {
        String redisVerifyCode = redisUtils.get(UserRedisPrefixEnum.USER_REGISTER_PHONE_CODE.getKey() + mobile);
        return StringUtils.equals(userVerifyCode, redisVerifyCode);
    }

    @Override
    public void registerToDb(RegisterRequest registerRequest) {

        ZxlUser zxlUser = (ZxlUser) new ZxlUser()
                .setId(idWorker.nextId() + "")
                .setUsername(registerRequest.getMobile())
                .setPassword(encoder.encode(registerRequest.getPassword()))
                .setPhone(registerRequest.getMobile())
                .setUpdatedAt(LocalDateTime.now())
                .setCreatedAt(LocalDateTime.now());
        userDao.save(zxlUser);
    }

    @Override
    public ZxlUser login(String phone, String password) {
        ZxlUser zxlUser = userDao.findByPhoneAndStatus(phone, UserStatusEnum.USER_VAILD.getStatus());
        if(zxlUser != null && encoder.matches(password, zxlUser.getPassword())) {
            return zxlUser;
        }
        return null;
    }

    @Override
    public Map<String, Object> issueSign(ZxlUser zxlUser, String roleName) {
        String token = jwtUtils.createJWT(zxlUser.getId(), zxlUser.getUsername(), UserRoleEnum.ROLE_USER.getRoleName());
        Map<String, Object> map = Maps.newHashMap();
        map.put("token", token);
        map.put("username", zxlUser.getUsername());
        map.put("countryCode", zxlUser.getCountryCode());
        return map;
    }

}
