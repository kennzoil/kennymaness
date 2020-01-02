package com.kennymaness.kennymaness.services;

import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.daos.UserDao;

import java.util.ArrayList;
import java.util.HashMap;


public class UserService {

    private static UserDao UserDao;

    private static ArrayList<User> users = new ArrayList<>();

    public static HashMap<String, String> findByFirstName(String first_name) {
        /*
        getByFirstName is a service method that the User Controller will use
        to obtain a specialized object containing data specific to the
        user whose first name is passed into the method's parameter.
        */

        // use the Dao to get the user by first name
        User user = UserDao.findByFirstName(first_name);

        // initialize empty userObject
        HashMap<String, String> userObject = new HashMap<>();

        // populate with data
        userObject.put("first_name", user.getFirstName());
        userObject.put("last_name", user.getLastName());
        userObject.put("username", user.getUsername());
        userObject.put("email", user.getEmail());

        return userObject;
    }


    public static HashMap<String, String> findByUsername(String username) {
        /*
        getByUsername is a service method that the User Controller will use
        to obtain a specialized object containing data specific to the
        user whose username is passed into the method's parameter.
        */

        // use the Dao to get the user by username
        User user = UserDao.findByUsername(username);

        // initialize empty userObject
        HashMap<String, String> userObject = new HashMap<>();

        // Populate with data
        userObject.put("first_name", user.getFirstName());
        userObject.put("last_name", user.getLastName());
        userObject.put("username", user.getUsername());
        userObject.put("email", user.getEmail());

        return userObject;
    }

    // add
    public static void add(User newUser) {
        users.add(newUser);
    }

    // remove
    public static void remove(int id) {
        User userToRemove = getById(id);
        users.remove(userToRemove);
    }

    // getById
    public static User getById(int id) {

        User theUser = null;

        for (User candidateUser : users) {
            if (candidateUser.getId() == id) {
                theUser = candidateUser;
            }
        }

        return theUser;
    }

    // UserDao setter
    public static void setUserDao(com.kennymaness.kennymaness.daos.UserDao userDao) {
        UserDao = userDao;
    }
}
