package com.epam.preprod.patsera.shop.bean;

import java.awt.image.BufferedImage;

public class Captcha {
    private String captchaString;
    private String captchaId;
    private BufferedImage image;
    private long currentTime;

    public Captcha(String captchaString, String captchaId, long liveTime, BufferedImage image) {
        this.captchaString = captchaString;

        this.captchaId = captchaId;
        this.image = image;
        this.currentTime = System.currentTimeMillis() + liveTime;
    }

    public String getCaptchaString() {
        return captchaString;
    }

    public void setCaptchaString(String captchaString) {
        this.captchaString = captchaString;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}