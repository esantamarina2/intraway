package com.intraway.test.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public abstract class AbstractHibernateDao implements DataSourceOperationDao {

    protected Logger log = Logger.getLogger(getClass());

    protected SessionFactory sessionFactory;

    protected Class<? extends Serializable> entityClass;

    public AbstractHibernateDao(SessionFactory sessionFactory) {
       this.sessionFactory=sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T extends Serializable> T findById(Serializable id, Class<T> entityClass) {

        T entity = getCurrentSession().get(entityClass, id);

        return entity;
    }

    @Transactional
    public <T extends Serializable> void save(final T entity) {
        log.debug("Save: persisting instance " + entity.toString());
        try {
            getCurrentSession().persist(entity);
        } catch (RuntimeException re) {
            log.error("Persist failed for " + entity.toString(), re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T extends Serializable> T update(final T entity) {
        log.debug("update: instance " + entity.toString());

        T mergedEntity;
        try {
            mergedEntity = (T) getCurrentSession().merge(entity);
        } catch (RuntimeException re) {
            log.error("Update: failed for " + entity.toString(), re);
            throw re;
        }

        return mergedEntity;
    }

    @Transactional
    public <T extends Serializable> void delete(final T entity) {
        log.debug("Delete: instance " + entity.toString());
        try {
            getCurrentSession().delete(entity);
        } catch (RuntimeException re) {
            log.error("Remove: failed for " + entity.toString(), re);
            throw re;
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
