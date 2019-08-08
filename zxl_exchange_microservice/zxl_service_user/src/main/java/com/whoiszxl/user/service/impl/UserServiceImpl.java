package com.whoiszxl.user.service.impl;

import com.whoiszxl.base.service.BaseServiceImpl;
import com.whoiszxl.user.mapper.UserMapper;
import com.whoiszxl.user.pojo.ZxlUser;
import com.whoiszxl.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @description: 用户服务实现类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, ZxlUser> implements UserService {
}
