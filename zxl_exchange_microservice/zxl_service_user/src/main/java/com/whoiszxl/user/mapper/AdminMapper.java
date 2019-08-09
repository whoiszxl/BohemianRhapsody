package com.whoiszxl.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoiszxl.user.pojo.ZxlAdmin;
import com.whoiszxl.user.pojo.ZxlUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<ZxlAdmin> {
}
