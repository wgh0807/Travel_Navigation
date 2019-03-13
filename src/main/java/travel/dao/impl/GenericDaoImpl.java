package travel.dao.impl;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import travel.dao.GenericDao;
import travel.util.Constants;
import travel.util.Pagination;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class GenericDaoImpl<T extends Serializable,ID extends Number> implements GenericDao<T,ID> {

    private SqlSession sqlSession;
    private String nameSpace;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    GenericDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> tClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        this.nameSpace = StringUtils.uncapitalize(tClass.getSimpleName());
    }

//    获取sqlId
    private String getStatement(String sqlId) {
        return this.nameSpace.concat(".").concat(sqlId);
    }

//    基本查询
    @Override
    public int create(T t) {
        return sqlSession.insert(getStatement("insert"),t);
    }

    @Override
    public int delete(ID id) {
        return sqlSession.delete(getStatement("delete"),id);
    }

    @Override
    public int modify(T t) {
        return sqlSession.update(getStatement("update"),t);
    }

    @Override
    public T queryOne(ID id) {
        return queryOne(getStatement("selectOne"),id);
    }

    @Override
    public List<T> queryAll() {
        return sqlSession.selectList(getStatement("selectAll"));
    }

    // 高级查询
    @Override
    public int create(String sqlId, Object parameter) {
        return sqlSession.insert(getStatement(sqlId),parameter);
    }

    @Override
    public int delete(String sqlId, Object parameter) {
        return sqlSession.delete(getStatement(sqlId), parameter);
    }

    @Override
    public int modify(String sqlId, Object parameter) {
        return sqlSession.update(getStatement(sqlId),parameter);
    }

    @Override
    public T queryOne(String sqlId, Object parameter) {
        return sqlSession.selectOne(getStatement(sqlId), parameter);
    }

    @Override
    public List<T> queryList(String sqlId, Object parameter) {
        return sqlSession.selectList(getStatement(sqlId), parameter);
    }

    @Override
    public List<T> queryAll(String sqlId) {
        return sqlSession.selectList(getStatement(sqlId));
    }

    @Override
    public Object query(String sqlId, Object parameter) {
        return sqlSession.selectOne(sqlId,parameter);
    }

//    分页查询
    @Override
    public Pagination<T> queryList(String sqlId, int currentPage) {
        return getPagination(sqlId,null,currentPage,0);
    }

    @Override
    public Pagination<T> queryAll(String sqlId, Object parameter, int currentPage) {
        return getPagination(sqlId,parameter,currentPage,0);
    }

    @Override
    public Pagination<T> queryList(String sqlId, int currentPage,int pageSize_1) {
        return getPagination(sqlId,null,currentPage,pageSize_1);
    }

    @Override
    public Pagination<T> queryAll(String sqlId, Object parameter, int currentPage, int pageSize_1) {
        return getPagination(sqlId,parameter,currentPage,pageSize_1);
    }

    /**
     * 分页查询
     * @param sqlId
     * @param parameter
     * @param currentPage
     * @param pageSize_1
     * @return
     */
    private Pagination<T> getPagination(String sqlId, Object parameter, int currentPage, int pageSize_1) {
        int pageSize = pageSize_1;
        int totalRows = sqlSession.selectList(getStatement(sqlId), parameter).size();

        if (pageSize <= 0) {
            pageSize = Constants.PAGE_SIZE;
        }
        int totalPages = (int) Math.ceil(totalRows / (double) pageSize);

        RowBounds rowBounds = new RowBounds(pageSize*(currentPage-1),pageSize);
        List<T> list = sqlSession.selectList(getStatement(sqlId),parameter,rowBounds);

        return new Pagination<T>(list, sqlId,pageSize,totalRows,totalPages,currentPage);
    }
}
