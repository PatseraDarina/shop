package com.epam.preprod.patsera.shop.service.captcha.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaStorageStrategy {
    void write(HttpServletRequest request, HttpServletResponse response, String captchaId);
    String read(HttpServletRequest request);
}
