package com.whoiszxl.core.service;

import com.whoiszxl.core.dao.BaseDao;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 最顶层service基类
 * @author: whoiszxl
 * @create: 2020-01-07
 **/
public class BaseService<E, D extends BaseDao> {

    @Autowired
    protected EntityManager entityManager;

    @Setter
    protected D dao;

    public E findById(Serializable id) {
        return (E) dao.getOne(id);
    }

    public List<E> findAll() {
        return dao.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        dao.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletes(Long[] ids) {
        for (long id : ids) {
            delete(id);
        }
    }

    public E save(E e) {
        return (E) dao.save(e);
    }

}
