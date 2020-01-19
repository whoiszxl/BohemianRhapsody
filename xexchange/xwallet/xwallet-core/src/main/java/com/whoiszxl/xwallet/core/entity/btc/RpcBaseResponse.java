package com.whoiszxl.xwallet.core.entity.btc;

import lombok.Data;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2020-01-10
 **/
@Data
public class RpcBaseResponse {

    private String result;
    private String error;
    private String id;
}
