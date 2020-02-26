package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.models.*;
import com.kennymaness.kennymaness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        return "index";
    }

    // render the blog page
    @RequestMapping(method = RequestMethod.GET, value = "blog")
    public String blogPageGet(Model model) { return "blog/blog"; }

    // render the users page
    @RequestMapping(method = RequestMethod.GET, value = "users")
    public String usersPageGet(Model model) {

        model.addAttribute("users", userService.listAll());

        return "users";
    }

    /**
     * The following handler methods are for authentication testing.
     */

    // render the admin page
    @RequestMapping(value="admin", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("username", "Welcome " + user.getUsername());
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    // render the user page
    @RequestMapping(method = RequestMethod.GET, value = "user")
    public String userPageGet() { return ("index"); }

}