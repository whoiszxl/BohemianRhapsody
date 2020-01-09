package com.whoiszxl.core.enums.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * 用户角色枚举
 * TODO 换成数据库方式
 */
@Getter
@AllArgsConstructor
public enum MemberRoleEnum {
    ROLE_ADMIN("claims_admin", "admin"),
    ROLE_USER("claims_member", "member")
    ;
    private String roleAttrKey;
    private String roleName;
}
