package com.epam.preprod.patsera.shop.dto;

import com.epam.preprod.patsera.shop.bean.User;

import javax.servlet.http.Part;

public class RegistrationFormDto {

    public RegistrationFormDto() {}

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Part image;
    private boolean getNews;
    private CaptchaDto captchaDto;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CaptchaDto getCaptchaDto() {
        return captchaDto;
    }

    public void setCaptchaDto(CaptchaDto captchaDto) {
        this.captchaDto = captchaDto;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isGetNews() {
        return getNews;
    }

    public void setGetNews(boolean getNews) {
        this.getNews = getNews;
    }

    public User createUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setGetNews(getNews);
        return user;
    }
}
