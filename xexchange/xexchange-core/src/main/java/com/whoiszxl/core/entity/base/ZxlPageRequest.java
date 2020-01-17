package com.whoiszxl.core.entity.base;

/**
 * @description: 分页请求实体
 * @author: whoiszxl
 * @create: 2019-09-05
 **/
public class ZxlPageRequest {

    private Integer pageNumber = 0;

    private Integer pageSize = 10;

    private String status;


    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

