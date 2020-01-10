package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.models.*;
import com.kennymaness.kennymaness.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("")
public class MainController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String renderHomepage(
    ){
        return "homepage";
    }

    /** This handler method merely renders the signup template. */
    @GetMapping("/signup")
    public String renderSignupPage() {return "signup";}

    /** This handler method receives data from the form in the signup template,
     * adds each piece of data as an attribute of its model,
     * uses the service method addUser to add a new user to the database using this data,
     * then dynamically renders the homepage template.
     * **/
    @PostMapping("/signup")
    public String signupForm(
            @RequestParam
            String first_name,
            String last_name,
            String username,
            String email,
            Model model
    ) {

    // add each piece of data as an attribute of the model
        model.addAttribute("title", "Sign Up");
        model.addAttribute("first_name", first_name);
        model.addAttribute("last_name", last_name);
        model.addAttribute("username", username);
        model.addAttribute("email", email);

    // add a new User object to the database
        User newUser = UserService.createUser(first_name, last_name, username, email);
        userDao.save(newUser);
        model.addAttribute("users", userDao.findAll());

    // render the homepage template
        return "homepage";
    }

}