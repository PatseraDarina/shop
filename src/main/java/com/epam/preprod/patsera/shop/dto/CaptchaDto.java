package com.epam.preprod.patsera.shop.dto;

public class CaptchaDto {
    private String string;
    private String id;

    public CaptchaDto(String string, String id) {
        this.string = string;
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
