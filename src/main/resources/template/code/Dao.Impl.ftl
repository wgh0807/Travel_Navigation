package ${package}.dao.impl;

import org.springframework.stereotype.Repository;
import ${package}.dao.${model}Dao;
import ${package}.obj.${model};

@Repository
public class ${model}DaoImpl extends GenericDaoImpl<${model},${PK}> implements ${model}Dao {
}
