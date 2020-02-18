package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.daos.UserDao;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.models.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public LoginController() {}

    // render the login page
    @RequestMapping(method = RequestMethod.GET, value = "login")
    public String loginPageGet(Model model) { return "login"; }

    //    TODO - configure login post request handler
    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String loginForm(String username, String password, Model model) {



        return "redirect:";
    }

    // logout
    @RequestMapping(method = RequestMethod.GET, value = "logout")
    public String logoutPageGet() { return "logout";}
}