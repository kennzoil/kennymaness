package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MainController {

    // instantiate UserDao object
    @Autowired
    private UserService userService;

    private User user;

    // render the homepage
    @RequestMapping(method = RequestMethod.GET)
    public String homepageGet(Model model) {
        return "example";
    }

    // render the users page
    @RequestMapping(method = RequestMethod.GET, value = "users")
    public String usersPageGet(Model model) {

        model.addAttribute("users", userService.listAll());

        return "users";
    }

}