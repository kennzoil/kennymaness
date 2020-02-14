package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.UserRepository;
import com.kennymaness.kennymaness.Validate;
import com.kennymaness.kennymaness.daos.UserDao;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.models.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class SignupController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    // render the signup page
    @RequestMapping(method = RequestMethod.GET, value = "signup")
    public String renderSignupPage() {return "signup";}

    @RequestMapping(method = RequestMethod.POST, value = "signup")
    public String signupForm(
            @RequestParam
            String username,
            String password,
            String passwordconfirm,
            Model model
    ) {
        // instantiate error messages
        String username_error = "";
        String password_error = "";
        String passwords_match = "";
        int error_count = 0;

        // if any form data is invalid,
        if (!Validate.validateUsername(username)) {
            username_error = "username must be between 3 and 25 characters";
            error_count++;
        }

        if (!Validate.validatePassword(password)) {
            password_error = "password must be at least 8 characters, including 1 uppercase letter, 1 lowercase letter, and 1 number";
            error_count++;
        }

        if (!password.equals(passwordconfirm)) {
            passwords_match = "make sure your passwords match";
            error_count++;
        }

        // add errors to model
        model.addAttribute("username_error", username_error);
        model.addAttribute("password_error", password_error);
        model.addAttribute("passwords_match", passwords_match);

        // if any errors are present, reload signup page
        if (error_count > 0) { return "/signup"; }

        // otherwise, carry on
        else if (error_count == 0) {

        // add each piece of data as an attribute of the model
        model.addAttribute("username", username);
        model.addAttribute("password", password);

        // add a new User object to the database
        User newUser = UserService.createUser(username, password);
        userDao.save(newUser);
        }

        // render the homepage template
        return "redirect:";
    }

    /*
    login
     */

    // render the login page
    @RequestMapping(method = RequestMethod.GET, value = "login")
    public String loginPageGet(Model model) { return "login"; }

    //    TODO - configure login post request handler
    //    @RequestMapping(method = RequestMethod.POST, value = "login")
    //    public String loginForm(String username, String password, Model model) { return "redirect:";}
}