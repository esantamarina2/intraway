package com.intraway.test.dao.impl;

import com.intraway.test.dao.AbstractHibernateDao;
import com.intraway.test.dao.UserDao;
import com.intraway.test.model.User;
import org.apache.log4j.Logger;
import org.hibernate.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl extends AbstractHibernateDao implements UserDao {
	
    private static Logger log = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        entityClass = User.class;
    }

    /**
     * Find all users
     */
    @Transactional
    public List<User>  getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            return getCurrentSession().createQuery(criteriaQuery).getResultList();
        } catch (HibernateException he) {
            log.error("getAllUsers: Error: " + he);
        }
        return users;
    }
    
    /**
     * Find user by userName
     */
    @Transactional
    public User getUserByName(String userName) {
        try {
            CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userName"),userName));
            User result =  getCurrentSession().createQuery(criteriaQuery).uniqueResult();
            return result;
        }
        catch (HibernateException he) {
            log.error("getUserByName: Error: " + he);
            throw he;
        }
    }

}
