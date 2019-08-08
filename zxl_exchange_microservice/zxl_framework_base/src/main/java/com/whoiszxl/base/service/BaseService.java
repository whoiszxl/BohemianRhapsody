package com.whoiszxl.base.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BaseService<T> extends IService<T> {

    /**
     * 查询所有数据
     * @return List<Student>
     */
    List<T> findAll();

    /**
     * 查询部分数据
     * @return List<Student>
     */
    List<T> findList();

    /**
     * 查询一条数据
     * @return Student
     */
    T findOne();

    /**
     * 根据主键ID查询数据
     * @param id 主键ID，为null，返回null
     * @return Student
     */
    T findById(Long id);
}
