package com.epam.preprod.patsera.shop.service.captcha.strategy.impl;

import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaStorageHiddenStrategyImpl implements CaptchaStorageStrategy {
    @Override
    public void write(HttpServletRequest request, HttpServletResponse response, String captchaId) {
        //throw new UnsupportedOperationException();
    }

    @Override
    public String read(HttpServletRequest request) {
        return request.getParameter(Constant.Captcha.CAPTCHA_ID);
    }
}
