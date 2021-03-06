package com.whoiszxl.core.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: zxl_user用户状态枚举
 * @author: whoiszxl
 * @create: 2019-08-09
 **/
@Getter
@AllArgsConstructor
public enum MemberStatusEnum {
    MEMBER_VAILD("有效", 1),
    MEMBER_INVAILD("无效", 0)
    ;
    private String msg;
    private Integer status;
}
