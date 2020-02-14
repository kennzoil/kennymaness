package com.kennymaness.kennymaness;

public class Validate {

    // username must be between 3 and 25 characters
    public static boolean validateUsername(String username) {
        return (username.length() < 25 || username.length() > 3);
    }

    // password must be at least 8 characters
    public static boolean validatePassword(String password) {
        if (password.length() > 7) { return checkPasswordParameters(password); }
        else { return false; }
    }

    private static boolean checkPasswordParameters(String password) {

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
}