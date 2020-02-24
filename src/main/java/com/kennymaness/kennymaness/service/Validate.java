package com.kennymaness.kennymaness.service;

public class Validate {

    // returns true if either the username, password, or password confirmation are invalid
    public static boolean invalid(String username, String password, String passwordconfirm) {
        int i = 0;
        if (!Validate.username(username)) { i++; }
        if (!Validate.password(password)) { i++; }
        if (!password.equals(passwordconfirm)) { i++; }
        return i != 0;
    }

    // returns true if the given username is between 4 and 24 characters in length
    public static boolean username(String username) {
        return (username.length() < 25 || username.length() > 3);
    }

    // returns true if the given password is at least 8 characters in length
    public static boolean password(String password) {
        if (password.length() > 7) { return passwordParameters(password); }
        else { return false; }
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

    // returns true if the given email address is valid
    public static boolean email(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}