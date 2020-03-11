package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.models.*;
import com.kennymaness.kennymaness.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    UserRepository userRepository;

    private MyUserDetails myUserDetails;

    private User user;

    // render the homepage
    @RequestMapping(method = RequestMethod.GET)
    public String homepageGet(Model model) {
        return "index";
    }

    // render the users page
    @RequestMapping(method = RequestMethod.GET, value = "users")
    public String usersPageGet(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    /**
     * The following handler methods are for authentication testing.
     */

    // render the admin page
//    @RequestMapping(value="admin", method = RequestMethod.GET)
//    public String adminPageGet(Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(auth.getName());
//        model.addAttribute("admin_message", user);
//        System.out.println("username");
//        return "admin";
//    }
//
//    // render the user page
//    @RequestMapping(value = "user", method = RequestMethod.GET)
//    public String userPageGet(Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(auth.getName());
//        model.addAttribute("user_message", "This page is just for you. Hooray!");
//        return "user";
//    }

}