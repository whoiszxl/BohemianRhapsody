package com.whoiszxl.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoiszxl.common.pojo.ZxlBanner;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 首页轮播表 Mapper 接口
 * </p>
 *
 * @author whoiszxl
 * @since 2019-08-08
 */
@Mapper
public interface BannerMapper extends BaseMapper<ZxlBanner> {
}
