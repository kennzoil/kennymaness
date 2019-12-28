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

        // add data to the model for use in the template
        // model.addAttribute("attribute", "value");

        User user = new User();
        user.setUsername("HEYYYYY");
        UserDao.save(user);

        return "homepage";
    }

//    @GetMapping("/greeting")
//    public String greeting( @RequestParam( name="name", required=false, defaultValue="World" ) String name, Model model) {
//        model.addAttribute("name", name);
//
//        System.out.println("Here is the value of name:");
//        System.out.println(name);
//
//        return "greeting";
//    }

}