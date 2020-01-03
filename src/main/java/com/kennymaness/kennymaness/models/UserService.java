package com.kennymaness.kennymaness.models;
import com.kennymaness.kennymaness.daos.*;

import java.util.ArrayList;

public class UserService {

    private static ArrayList<User> users = new ArrayList<>();

    // getAll
    public static ArrayList<User> getAll() {
        return users;
    }

    // add a new User to the database
    public static User createUser(String first_name, String last_name, String username, String email) {

        User newUser = new User(first_name, last_name, username, email);

        return newUser;
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

}