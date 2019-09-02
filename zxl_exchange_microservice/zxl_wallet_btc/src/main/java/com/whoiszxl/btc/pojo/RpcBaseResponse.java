package com.whoiszxl.btc.pojo;

import lombok.Data;

/**
 * @description:
 * @author: whoiszxl
 * @create: 2019-08-31
 **/
@Data
public class RpcBaseResponse {
    private String result;
    private String error;
    private String id;
}
