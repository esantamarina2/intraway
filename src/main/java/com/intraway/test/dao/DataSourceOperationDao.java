package com.intraway.test.dao;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface DataSourceOperationDao {

    <T extends Serializable> T findById(Serializable id, Class<T> entityClass);

    <T extends Serializable> void save(T entity);

    <T extends Serializable> T update(T entity);

    <T extends Serializable> void delete(T entity);
}
