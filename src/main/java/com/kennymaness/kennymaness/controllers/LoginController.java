package com.kennymaness.kennymaness.controllers;

import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.service.LoginForm;
import com.kennymaness.kennymaness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
//import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private RequestCache requestCache = new HttpSessionRequestCache();

//    @RequestMapping("authenticate")
//    public String authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        boolean authenticate = request.authenticate(response);
//        return authenticate ? "index" : null;
//    }

    // login get request
    @RequestMapping(method = RequestMethod.GET, value = "login")
    public String loginPageGet(@ModelAttribute LoginForm loginForm) { return "login"; }

    // login post request
    public String processLoginForm(
            @ModelAttribute @Valid User newUser,
            Errors errors,
            String verify,
            Model model
    ){
        if (errors.hasErrors() || !verify.equals(newUser.getPassword())) {
            model.addAttribute("user", newUser);
            return "signup";
        }
        else if (userRepository.findByUsername(newUser.getUsername()) != null){
            model.addAttribute("existerror", "User Already Exists");
            return "signup";
        }
        else {
            String hashedPassword = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(hashedPassword);
            newUser.setActive(true);
            newUser.setAssignedRole("USER");
            //User newestUser = new User(newUser.getUsername(), hashedPassword, newUser.getEmail(), true, "ROLE_USER");
            userRepository.save(newUser);
            model.addAttribute("username", newUser.getUsername());
            return "redirect:user";
        }
    }
//    @RequestMapping(method = RequestMethod.POST, value = "login")
//    public String login(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @ModelAttribute LoginForm loginForm,
//            BindingResult result
//    ) throws ServletException {
//        try {
//            request.login(loginForm.getUsername(), loginForm.getPassword());
//            SavedRequest savedRequest = requestCache.getRequest(request, response);
//            if (savedRequest != null) {
//                return "redirect:" + savedRequest.getRedirectUrl();
//            } else {
//                return "redirect:/";
//            }
//
//        } catch (ServletException authenticationFailed) {
//            result.rejectValue(null, "authentication.failed");
//            return "login";
//        }
//    }

    // logout
    @RequestMapping(method = RequestMethod.GET, value = "logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}