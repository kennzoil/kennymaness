package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.*;
import com.kennymaness.kennymaness.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {


    @Autowired
    private UserDao UserDao;

    @GetMapping("/")
    public String renderHomepage( Model model ) {

        return "homepage";
    }

    /** This handler method merely renders the signup template. */
    @GetMapping("/signup")
    public String renderSignupPage() {
        return "signup";
    }

    /** This handler method receives data from the form in the signup template,
     * adds each piece of data as an attribute of the model,
     * instantiates a new User object,
     * uses that object's setters to populate it with its respective attributes,
     * uses the DAO to save that object to the database,
     * then renders the signup template.
     * **/
    @PostMapping("/signup")
    public String signupForm(

    // receive data from the form in the signup template
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

    // instantiate a new User object
        User user = new User();

    // use that object's setters to populate it with its respective attributes
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setUsername(username);
        user.setEmail(email);

    // use the DAO to save that object to the database
        UserDao.save(user);

    // render the signup template
        return "signup";
    }

}