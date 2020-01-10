package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.models.*;
import com.kennymaness.kennymaness.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainController {

    // instantiate UserDao object
    @Autowired
    private UserDao userDao;

    private User user;

    // render the homepage
    @GetMapping
    public String renderHomepage(Model model) {

        model.addAttribute("users", userDao.findAll());

        return "homepage";
    }
}