package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.UserDao;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.models.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    @Autowired
    private UserDao userDao;

    /** This handler method merely renders the signup template. */
    @GetMapping
    public String renderSignupPage() {return "signup";}

    /** This handler method receives data from the form in the signup template,
     * adds each piece of data as an attribute of its model,
     * uses the service method addUser to add a new user to the database using this data,
     * then dynamically renders the homepage template.
     * **/
    @PostMapping
    public String signupForm(
            @RequestParam
            String first_name,
            String last_name,
            String username,
            String email,
            Model model
    ) {

        // add each piece of data as an attribute of the model
        model.addAttribute("first_name", first_name);
        model.addAttribute("last_name", last_name);
        model.addAttribute("username", username);
        model.addAttribute("email", email);

        // add a new User object to the database
        User newUser = UserService.createUser(first_name, last_name, username, email);
        userDao.save(newUser);

        // render the homepage template
        return "users";
    }

}
