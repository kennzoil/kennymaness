package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.service.Validate;
import com.kennymaness.kennymaness.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    // render signup page
    @RequestMapping(method = RequestMethod.GET)
    public String renderSignupPage() {return "registration/signup";}

    // signup form handler
    @RequestMapping(method = RequestMethod.POST)
    public String signupForm(
            @RequestParam
            User user,
            String username,
            String password,
            String passwordconfirm,
            Model model
    ) {
        // initialize error messages
        String username_error;
        String password_error;
        String passwords_match;

        // Username must not be already taken
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            username_error = "Someone's already using that username.";
            model.addAttribute("username_error", username_error);
            return "registration/signup";
        }

        // if any errors are present,
        if (!Validate.validSignup(username, password, passwordconfirm)) {

            // add corresponding error messages as model attributes
            if (!Validate.username(username)) {
                username_error = "username must be between 3 and 25 characters";
                model.addAttribute("username_error", username_error);
            } else if (!Validate.password(password)) {
                password_error = "password must be at least 8 characters, an uppercase and lowercase letter, and 1 number";
                model.addAttribute("password_error", password_error);
            } else if (!password.equals(passwordconfirm)) {
                passwords_match = "make sure your passwords match";
                model.addAttribute("passwords_match", passwords_match);
            }

            // re-render signup page
            model.addAttribute("username", username);
            return "registration/signup";

        } else {

            // add each piece of data as a model attribute
            model.addAttribute("username", username);
            model.addAttribute("password", password);

            // add a new User object to the database
            User newUser = new User();
            newUser.setUsername(username);
            String hashedPassword = passwordEncoder.encode(password);
            newUser.setPassword(hashedPassword);
            newUser.setAssignedRole("USER");
            userRepository.save(newUser);

            // redirect to homepage
            return "redirect:/login";
        }
    }
}