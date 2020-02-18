package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.Validate;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.models.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    public SignupController() {}

    /* Signup */

    // render signup page
    @RequestMapping(method = RequestMethod.GET)
    public String renderSignupPage() {return "signup";}

    // signup form handler
    @RequestMapping(method = RequestMethod.POST)
    public String signupForm(
            @RequestParam
            String username,
            String password,
            String passwordconfirm,
            Model model
    ) {
        // initialize error messages
        String username_error;
        String password_error;
        String passwords_match;

        // if any errors are present,
        if (Validate.invalid(username, password, passwordconfirm)) {

            // add corresponding error messages as model attributes
            if (!Validate.username(username)) {
                username_error = "username must be between 3 and 25 characters";
                model.addAttribute("username_error", username_error);
            } else if (!Validate.password(password)) {
                password_error = "password must be at least 8 characters, including 1 uppercase letter, 1 lowercase letter, and 1 number";
                model.addAttribute("password_error", password_error);
            } else if (!password.equals(passwordconfirm)) {
                passwords_match = "make sure your passwords match";
                model.addAttribute("passwords_match", passwords_match);
            }

            // re-render signup page
            return "/signup";

        } else {

            // add each piece of data as a model attribute
            model.addAttribute("username", username);
            model.addAttribute("password", password);

            // add a new User object to the database
            User newUser = UserService.createUser(username, password);
            userService.saveUser(newUser);

            // redirect to homepage
            return "redirect:";
        }
    }
}