package com.whoiszxl.user.service.impl;

import com.whoiszxl.base.service.BaseServiceImpl;
import com.whoiszxl.user.mapper.AdminMapper;
import com.whoiszxl.user.pojo.ZxlAdmin;
import com.whoiszxl.user.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @description: 用户服务实现类
 * @author: whoiszxl
 * @create: 2019-08-08
 **/
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, ZxlAdmin> implements AdminService {
}
