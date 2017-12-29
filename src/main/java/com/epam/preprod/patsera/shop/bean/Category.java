package com.epam.preprod.patsera.shop.bean;

import com.epam.preprod.patsera.shop.repository.BaseEntity;

import java.io.Serializable;

public class Category extends BaseEntity<String> implements Serializable {

    private String name;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
