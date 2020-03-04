package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.BlogPostRepository;
import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.models.BlogPost;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.security.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    private BlogPostRepository blogPostRepository;
    private UserRepository userRepository;


    // render the blog page
    @RequestMapping(method = RequestMethod.GET)
    public String blogPageGet(Model model) {
        model.addAttribute("blogPosts", blogPostRepository.findAll());
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

    // add blog post POST handler
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddPostForm(
            String title,
            String description,
            MultipartFile file,
            String snippet,
            Model model
    ) throws IOException {

        // initialize error messages and error message counter

        // ERROR 1
        System.out.println("\nope! #1\n");

        String titleerrors = "";
        String descriptionerrors = "";
        String snippeterrors = "";

        // ERROR 2
        System.out.println("\nope! #2\n");

        int errors=0;

        System.out.println("\nope! #3\n");
        if (title.length() > 25 || title.length() < 3) {
            titleerrors = "Title must be between 3 and 25 Characters long";
            errors++;
        }
        if (description.length() < 1 || description.length() > 100000) {
            descriptionerrors = "Description must be between 1 and 100000 Characters";
            errors++;
        }
        if (snippet.length() > 50) {
            snippeterrors = "Must be less than 50 Characters";
            errors++;
        }
        model.addAttribute("titleerrors", titleerrors);
        model.addAttribute("descriptionerrors", descriptionerrors);
        model.addAttribute("snippeterrors", snippeterrors);
        model.addAttribute("title", "Add Blog Post");
        if(errors>0){
            BlogPost blogPost = new BlogPost();
            blogPost.setSnippet(snippet);
            blogPost.setDescription(description);
            blogPost.setTitle(title);
            model.addAttribute("blogPost", blogPost);
            System.out.println("\nope! #3\n");
            return "blog/add";
        }
//        byte [] img = file.getBytes();
        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setTitle(title);
        newBlogPost.setDescription(description);
//        newBlogPost.setPostImage(img);
        newBlogPost.setSnippet(snippet);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //TODO: figure out what's up with this next line and how it works
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(myUserDetails.getUsername());
        newBlogPost.setUser(user);
        newBlogPost.setDate(new Date());
        blogPostRepository.save(newBlogPost);
        System.out.println("it worked");
        return "redirect:/";
    }
}