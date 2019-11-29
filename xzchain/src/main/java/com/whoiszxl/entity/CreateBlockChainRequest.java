package com.whoiszxl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 创建区块链的请求实体类
 * @author: whoiszxl
 * @create: 2019-11-29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlockChainRequest {

    private String address;
}
