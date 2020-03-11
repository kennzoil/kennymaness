package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.BlogPostRepository;
import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.models.BlogPost;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.security.MyUserDetails;
import com.kennymaness.kennymaness.service.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // render the blog page
    @RequestMapping(value="", method=RequestMethod.GET)
    public String blogPageGet(Model model) {
        model.addAttribute("blogPosts", blogPostRepository.findAll());
        return "blog/blog";
    }

    // add blog post GET handler
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String displayAddPostForm() {return "blog/add";}

    // add blog post POST handler
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String processAddPostForm(
            String title,
            String description,
//            MultipartFile file,
            String snippet,
            Model model
    ) throws IOException {

        // if any errors are present,
        if (!Validate.validBlogPost(title, description, snippet)) {

            // add corresponding error messages as model attributes
            if (!Validate.blogPostTitle(title)) {
                String title_error = "Title must be between 3 and 25 Characters long";
                model.addAttribute("title_error", title_error);
            } else if (!Validate.blogPostDescription(description)) {
                String description_error = "Description must be between 1 and 100000 Characters";
                model.addAttribute("description_error", description_error);
            } else if (!Validate.blogPostSnippet(snippet)) {
                String snippet_error = "Must be less than 50 Characters";
                model.addAttribute("snippet_error", snippet_error);
            }
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            model.addAttribute("snippet", snippet);

            // then reload the page
            return "/blog/add";
        } else {

//        byte [] img = file.getBytes();
            BlogPost newBlogPost = new BlogPost();
            newBlogPost.setTitle(title);
            newBlogPost.setDescription(description);
//        newBlogPost.setPostImage(img);
            newBlogPost.setSnippet(snippet);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            Optional<User> user = userRepository.findByUsername(myUserDetails.getUsername());

//            newBlogPost.setUser(user);
            newBlogPost.setDate(new Date());
            blogPostRepository.save(newBlogPost);
            return "redirect:/";
        }
    }
}