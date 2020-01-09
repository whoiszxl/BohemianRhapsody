package com.whoiszxl.core.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author whoiszxl
 * @since 2020-01-02
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "cms_country")
public class CmsCountry {

    @Id
    private String zhName;

    private String areaCode;

    private String enName;

    private String language;

    private String localCurrency;

    private Integer sort;


}
