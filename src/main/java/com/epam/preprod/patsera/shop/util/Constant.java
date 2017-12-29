package com.epam.preprod.patsera.shop.util;

public final class Constant {

    private Constant() {}

    public static final String AVATAR = "avatar";
    public static final String EMPTY = "";
    public static final String DEFAULT_AVATAR = "D:\\Preprod\\IdeaProjects\\shop\\src\\main\\webapp\\images\\user\\default-avatar.jpg";
    public static final String ENCODING = "UTF-8";
    public static final String PATH_AVATARS = System.getProperty("user.home") + "\\avatar\\";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String JPG = ".jpg";

    public static class Error {
        public static final String INVALID_NAME = "Name have to consist of latin, at least 3 letter.";
        public static final String INVALID_EMAIL = "Invalid email.";
        public static final String INVALID_PASSWORD = "Password have to consist of letters and digit. At least 6 symbols";
        public static final String PASSWORD_MATCHING = "Passwords does not match";
        public static final String USER_EXIST_ER = "User with such email already exist";
        public static final String INVALID_CAPTCHA_DATA = "Captcha does not match or registration time expired. You must register within 1 minute";
        public static final String REGISTRATION_ERROR = "During registration something went wrong. Repeat one more, please";
        public static final String LOGIN_ERROR = "During authorization something went wrong. Repeat one more, please";
        public static final String LOGIN_DATA_ERROR = "Invalid email or password";
        public static final String FAIL_IMAGE = "Fail to get image ";

    }

    public static class JSP {
        public static final String CABINET_JSP = "/shop/cabinet.jsp";
        public static final String REGISTRATION_JSP = "registration.jsp";
        public static final String INDEX_JSP = "index.jsp";

    }

    public static class Bean {
        public static final String USER = "user";
        public static final String LOGIN_DATA = "loginData";
    }

    public static class HTML {
        public static final String IMG_SRC = "<p><img src=\"";

        public static final String CAPTCHA = "/captcha\" name=\"captcha\" id=\"captcha\" "
                + "onclick=\"document.getElementById('captcha').src = '/shop/captcha?' + (new Date().getTime())\" "
                + "title=\"Click to update captcha\"/></p>";

        public static final String PLACEHOLDER = "<input type=\"text\" name=\"guess-captcha\""
                + "id=\"guess-captcha\""
                + "placeholder=\"Captcha\"/>";


        public static final String ERROR_SPAN = "<span id=\"captchaErr\"><c:out value=\"${sessionScope.userRegErrors.captcha}\"/></span>\n";
        public static String getHiddenField(String id) {
            return "<input type=\"hidden\" name=\"captchaId\" value=\"" + id + "\"/>";
        }

    }

    public static class Captcha {
        public static final String SESSION_STORAGE = "sessionStorage";
        public static final String COOKIE_STORAGE = "cookieStorage";
        public static final String HIDDEN_STORAGE = "hiddenStorage";
        public static final String CAPTCHA_LIVE_TIME = "captchaLiveTime";
        public static final String CAPTCHA_STORAGE = "captchaStorage";
        public static final String CAPTCHA_STRATEGY = "captchaStrategy";
        public static final String CAPTCHA_SERVICE = "captchaService";
        public static final String CAPTCHA_ID = "captchaId";
        public static final String GUESS_CAPTCHA = "guess-captcha";
    }

    public static class Attribute {
        public static final String USER_SERVICE = "userService";
        public static final String REGISTRATION_VALIDATOR = "registrationValidator";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String CONFIRM_PASSWORD = "confirmPassword";
        public static final String NEWS = "news";
        public static final String CAPTCHA = "captcha";
        public static final String USER_EXIST = "userExist";
        public static final String USER_REPOSITORY = "userRepository";
        public static final String USER_REGISTRATION_ERRORS = "userRegErrors";
        public static final String USER_LOGIN_ERRORS = "userLoginErrors";

    }
}
