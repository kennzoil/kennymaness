package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.models.BlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    // render the blog page
    @RequestMapping(value="", method = RequestMethod.GET)
    public String blogPageGet(Model model) {
        return "blog/blog";
    }

    // add blog post GET handler
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String displayAddPostForm(Model model) {
        model.addAttribute("title", "Add Blog Post");
        model.addAttribute("blogPost", new BlogPost());
        model.addAttribute("titleerrors", "");
        model.addAttribute("descriptionerrors", "");
        model.addAttribute("snippeterrors", "");
        return "blog/add";
    }
}
