package com.kennymaness.kennymaness.models;
//import com.kennymaness.kennymaness.daos.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserService {

//    @Autowired
//    private UserDao userDao;

    private static ArrayList<User> users = new ArrayList<>();

    // getAll
    public static ArrayList<User> getAll() {

        return users;
    }

    // create new User object with parameters
    public static User createUser(String first_name, String last_name, String username, String email) {

        User newUser = new User(first_name, last_name, username, email);

        return newUser;
    }

    // remove
    public void remove(int id) {
        User userToRemove = getById(id);
        users.remove(userToRemove);
    }

    // getById
    public User getById(int id) {

        User theUser = null;

        for (User candidateUser : users) {
            if (candidateUser.getId() == id) {
                theUser = candidateUser;
            }
        }

        return theUser;
    }

}