package com.kennymaness.kennymaness.service;

import javax.validation.Valid;

public class Validate {

    // returns true if either the username, password, or password confirmation are invalid
    public static boolean validSignup(String username, String password, String passwordconfirm) {
        return (Validate.username(username) && Validate.password(password) && password.equals(passwordconfirm));
    }

    // returns true if the given username is between 4 and 24 characters in length
    public static boolean username(String username) {return (username.length() < 25 || username.length() > 3);}

    // returns true if the given password is at least 8 characters in length
    public static boolean password(String password) {
        if (password.length() > 7) {return passwordParameters(password);}
        else {return false;}
    }

    // returns true if the given password contains an uppercase letter, a lowercase letter, and a number
    private static boolean passwordParameters(String password) {

        boolean hasNumber = false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        char c;

        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }

            if (hasNumber && hasUppercase && hasLowercase) {
                return true;
            }
        }
        return false;
    }

    /*
    EMAIL VALIDATION CURRENTLY NOT IN USE
     */

//    // returns true if the given email address is valid
//    public static boolean email(String email) {
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//        return email.matches(regex);
//    }

    /*
    BLOG POST VALIDATION
     */

    // returns true if either the blog post title, description, or snippet is invalid
    public static boolean validBlogPost(String blogPostTitle, String blogPostDescription, String blogPostSnippet) {
        return (Validate.blogPostTitle(blogPostTitle) && Validate.blogPostDescription(blogPostDescription) && Validate.blogPostSnippet(blogPostSnippet));
    }

    // returns true if blog post title is between 3 and 25 characters long
    public static boolean blogPostTitle(String title) {return title.length() < 25 || title.length() > 3;}

    // returns true if description is between 1 and 100,000 characters long
    public static boolean blogPostDescription(String description) {return description.length() < 100000 || description.length() > 1;}

    // returns true if snippet is less than 50 characters long
    public static boolean blogPostSnippet(String snippet) {return snippet.length() < 50;}
}