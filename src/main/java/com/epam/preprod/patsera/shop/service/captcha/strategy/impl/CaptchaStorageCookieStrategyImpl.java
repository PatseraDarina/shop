package com.epam.preprod.patsera.shop.service.captcha.strategy.impl;

import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaStorageCookieStrategyImpl implements CaptchaStorageStrategy {

    @Override
    public void write(HttpServletRequest request, HttpServletResponse response, String captchaId) {
        Cookie cookie = new Cookie(Constant.Captcha.CAPTCHA_ID, captchaId);
        response.addCookie(cookie);
    }

    @Override
    public String read(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String captchaId = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constant.Captcha.CAPTCHA_ID)) {
                captchaId = cookie.getValue();
            }
        }
        return captchaId;
    }
}
