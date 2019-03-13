package travel.service;

import travel.util.Pagination;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T extends Serializable, ID extends Number> {
    //    基本查询
    int create(T t);

    int delete(ID id);

    int modify(T t);

    T queryOne(ID id);

    List<T> queryAll();

    //    特殊查询
    int create(String sqlId, Object parameter);

    int delete(String sqlId, Object parameter);

    int modify(String sqlId, Object parameter);

    T queryOne(String sqlId, Object parameter);

    List<T> queryList(String sqlId, Object parameter);

    List<T> queryAll(String sqlId);

    Object query(String sqlId, Object parameter);

    //    分页查询
    Pagination<T> queryList(String sqlId, int currentPage);

    Pagination<T> queryAll( String sqlId, Object parameter, int currentPage);

    Pagination<T> queryList(String sqlId, int currentPage, int pageSize_1);

    Pagination<T> queryAll(String sqlId, Object parameter, int currentPage, int pageSize_1);
}
