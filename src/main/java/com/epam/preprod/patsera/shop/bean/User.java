package com.epam.preprod.patsera.shop.bean;

import com.epam.preprod.patsera.shop.repository.BaseEntity;

import java.io.Serializable;

public class User extends BaseEntity<String> implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean getNews;

    public User() {
    }

    public User(String id, String firstName, String lastName, String password, String email, boolean getNews) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.getNews = getNews;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public boolean isGetNews() {
        return getNews;
    }

    public void setGetNews(boolean getNews) {
        this.getNews = getNews;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
