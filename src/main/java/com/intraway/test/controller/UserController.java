package com.intraway.test.controller;

import com.intraway.test.dao.HibernateUtil;
import com.intraway.test.dao.impl.UserDAOImpl;
import com.intraway.test.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UserController {

    private final UserDAOImpl userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());

    /**
     * Find by userName
     * @param userName
     * @return
     */
    @GetMapping(value = "/{userName}")
    public User getUserByName(@PathVariable String userName) {
        User user = userDAO.getUserByName(userName);
        if (user == null) {
            throw new WebApplicationException("User Not Found", Response.Status.NOT_FOUND);
        }
        return user;
    }

    /**
     * Find by all
     * @return
     */
    @GetMapping(value = "/all")
    public List<User> getAllUsers()
    {
        return userDAO.getAllUsers();
    }

    /**
     * Create User
     * @param user
     * @return
     */
    @PostMapping("/")
    public Response createUser(@RequestBody User user) {
        if (userDAO.getUserByName(user.getUserName()) != null) {
            throw new WebApplicationException("User name already exist", Response.Status.BAD_REQUEST);
        }
        userDAO.save(user);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Update user by userName
     * @param userName
     * @param user
     * @return
     */
    @PutMapping("/{userName}")
    public Response updateUser(@PathVariable String userName, @RequestBody User user) {
        User retrievedUser = userDAO.getUserByName(userName);
        if (retrievedUser == null) {
            throw new WebApplicationException("User Not Found", Response.Status.NOT_FOUND);
        }
        try {
            userDAO.update(user);
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Delete by User Name
     * @param userName
     * @return
     */
    @DeleteMapping("/{userName}")
    public Response deleteUser(@PathVariable String userName) {
        try {
            userDAO.delete(userDAO.getUserByName(userName));
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
