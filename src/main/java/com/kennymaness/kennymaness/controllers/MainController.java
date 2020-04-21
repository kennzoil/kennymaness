package com.kennymaness.kennymaness.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainController {

    // render the homepage
    @RequestMapping(method = RequestMethod.GET)
    public String homepageGet(Model model) {
        return "index";
    }
}