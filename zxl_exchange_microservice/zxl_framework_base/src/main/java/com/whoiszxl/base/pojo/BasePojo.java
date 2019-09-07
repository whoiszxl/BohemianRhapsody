package com.whoiszxl.base.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BasePojo {

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    public Long getCreatedAt() {
        return createdAt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public Long getUpdatedAt() {
        return updatedAt.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
