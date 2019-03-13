package ${package}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${package}.dao.GenericDao;
import ${package}.obj.${model};
import ${package}.service.${model}Service;

@Service
public class ${model}ServiceImpl extends GenericServiceImpl<${model},${PK}> implements ${model}Service {
    @Override
    @Autowired
    void setGenericDao(GenericDao<${model}, ${PK}> genericDao) {
        this.genericDao = genericDao;
    }
}
