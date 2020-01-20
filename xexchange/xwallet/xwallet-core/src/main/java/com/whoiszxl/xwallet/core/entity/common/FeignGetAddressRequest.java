package com.whoiszxl.xwallet.core.entity.common;

import lombok.Data;

/**
 * @description: feign远程创建地址请求体
 * @author: whoiszxl
 * @create: 2020-01-14
 **/
@Data
public class FeignGetAddressRequest {

    private Long memberId;

    private String password;

}
