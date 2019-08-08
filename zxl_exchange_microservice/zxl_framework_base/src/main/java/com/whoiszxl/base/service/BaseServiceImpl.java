package com.whoiszxl.base.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T>  extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public List<T> findAll() {
        return list(null);
    }

    @Override
    public List<T> findList() {
        return list(null);
    }

    @Override
    public T findOne() {
        return getOne(null);
    }

    @Override
    public T findById(Long id) {
        return getById(id);
    }
}
