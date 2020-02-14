package com.kennymaness.kennymaness.models;
import com.kennymaness.kennymaness.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    // listAll
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(users::add);
        return users;
    }

    // create new User object with parameters
    public static User createUser(String username, String password) {

        User newUser = new User(username, password);

        return newUser;
    }

}