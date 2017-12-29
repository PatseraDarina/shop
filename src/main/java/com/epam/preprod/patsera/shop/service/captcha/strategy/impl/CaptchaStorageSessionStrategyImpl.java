package com.epam.preprod.patsera.shop.service.captcha.strategy.impl;

import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaStorageSessionStrategyImpl implements CaptchaStorageStrategy {
    @Override
    public void write(HttpServletRequest request, HttpServletResponse response, String captchaId) {
        HttpSession session = request.getSession();
        session.setAttribute(Constant.Captcha.CAPTCHA_ID, captchaId);
    }

    @Override
    public String read(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(Constant.Captcha.CAPTCHA_ID);
    }
}
