package com.whoiszxl.open.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 主键的导航栏，8个icon
 * @author: whoiszxl
 * @create: 2019-09-29
 **/

@Data
@Accessors(chain = true)
public class IndexCommonNavigator {

    private String navName;
    private String jumpUrl;
    private String imageUrl;

}
