package com.epam.preprod.patsera.shop.bean;

import com.epam.preprod.patsera.shop.repository.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product extends BaseEntity<String> implements Serializable {

    private String name;
    private BigDecimal price;
    private String idProducer;
    private String idCategory;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(String idProducer) {
        this.idProducer = idProducer;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
}
