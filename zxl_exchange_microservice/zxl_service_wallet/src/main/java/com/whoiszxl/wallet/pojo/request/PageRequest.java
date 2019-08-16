package com.whoiszxl.wallet.pojo.request;

import com.whoiszxl.base.enums.normal.SwitchStatusEnum;
import lombok.Data;

/**
 * @description: 分页查询请求类
 * @author: whoiszxl
 * @create: 2019-08-15
 **/
@Data
public class PageRequest {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer status;

}
