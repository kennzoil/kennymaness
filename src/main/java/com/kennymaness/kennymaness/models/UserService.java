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
//    public static List<User> listAll() {
//        List<User> users = new ArrayList<>();
//        userDao.findAll().forEach(users::add);
//        return users;
//    }

    // create new User object with parameters
    public static User createUser(String first_name, String last_name, String username, String email) {

        User newUser = new User(first_name, last_name, username, email);

        return newUser;
    }

}