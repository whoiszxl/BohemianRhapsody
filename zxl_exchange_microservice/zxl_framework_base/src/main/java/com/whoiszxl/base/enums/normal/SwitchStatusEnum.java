package com.whoiszxl.base.enums.normal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 正常的开关状态枚举
 * @author: whoiszxl
 * @create: 2019-08-15
 **/
@Getter
@AllArgsConstructor
public enum SwitchStatusEnum {

    STATUS_OPEN("开启", 1),
    STATUS_CLOSE("关闭", 0),
    STATUS_ALL("所有", 2);
    private String statusMsg;
    private Integer statusCode;

}
