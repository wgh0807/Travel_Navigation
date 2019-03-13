package travel.service.impl;

import org.springframework.stereotype.Service;
import travel.dao.GenericDao;
import travel.service.GenericService;
import travel.util.Pagination;

import java.io.Serializable;
import java.util.List;

public abstract class GenericServiceImpl<T extends Serializable, ID extends Number> implements GenericService<T,ID> {

    GenericDao<T, ID> genericDao;

    abstract void setGenericDao(GenericDao<T,ID> genericDao);


    @Override
    public int create(T t) {
        return genericDao.create(t);
    }

    @Override
    public int delete(ID id) {
        return genericDao.delete(id);
    }

    @Override
    public int modify(T t) {
        return genericDao.modify(t);
    }

    @Override
    public T queryOne(ID id) {
        return genericDao.queryOne(id);
    }

    @Override
    public List<T> queryAll() {
        return genericDao.queryAll();
    }

    @Override
    public int create(String sqlId, Object parameter) {
        return genericDao.create(sqlId,parameter);
    }

    @Override
    public int delete(String sqlId, Object parameter) {
        return genericDao.delete(sqlId,parameter);
    }

    @Override
    public int modify(String sqlId, Object parameter) {
        return genericDao.modify(sqlId, parameter);
    }

    @Override
    public T queryOne(String sqlId, Object parameter) {
        return genericDao.queryOne(sqlId, parameter);
    }

    @Override
    public List<T> queryList(String sqlId, Object parameter) {
        return genericDao.queryList(sqlId, parameter);
    }

    @Override
    public List<T> queryAll(String sqlId) {
        return genericDao.queryAll(sqlId);
    }

    @Override
    public Object query(String sqlId, Object parameter) {
        return genericDao.query(sqlId,parameter);
    }

    @Override
    public Pagination<T> queryList(String sqlId, int currentPage) {
        return genericDao.queryList(sqlId, currentPage);
    }

    @Override
    public Pagination<T> queryAll(String sqlId, Object parameter, int currentPage) {
        return genericDao.queryAll(sqlId, parameter, currentPage);
    }

    @Override
    public Pagination<T> queryList(String sqlId, int currentPage, int pageSize_1) {
        return genericDao.queryAll(sqlId,currentPage,pageSize_1);
    }

    @Override
    public Pagination<T> queryAll(String sqlId, Object parameter, int currentPage, int pageSize_1) {
        return genericDao.queryAll(sqlId, parameter, currentPage, pageSize_1);
    }
}
