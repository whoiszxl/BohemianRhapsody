package com.whoiszxl.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @description: zxl_config表对应实体类
 * @author: whoiszxl
 * @create: 2019-09-29
 **/
@Data
@Accessors(chain = true)
@Entity
@Table(name = "zxl_config")
public class ZxlConfig {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @Id
    private String id;

    private String key;

    private String value;

    private String remark;
    /**
     * 开启状态：0->关闭；1->开启
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

}
