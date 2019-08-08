package com.whoiszxl.base.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BasePojo {

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
