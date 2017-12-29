package com.epam.preprod.patsera.shop.util;

public final class Validator {

    private static final String STRING_REGEX = "^[a-zA-Zа-яА-Я]{3,15}$";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9!@#$%^&*()_]{6,20}$";
    private static final String CAPTCHA_REGEX = "[a-zA-Z0-9]{6}";

    private Validator() {}

    public static boolean isValidName(String name) {
        return name.matches(STRING_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
    public static boolean isValidCaptcha(String captcha) {
        return captcha.matches(CAPTCHA_REGEX);
    }
}
