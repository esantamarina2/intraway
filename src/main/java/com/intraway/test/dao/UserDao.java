package com.intraway.test.dao;

import com.intraway.test.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDao {

    List<User> getAllUsers();

    User getUserByName(String userName);

}
