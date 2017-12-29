package com.epam.preprod.patsera.shop.web.controller;

import com.epam.preprod.patsera.shop.bean.Captcha;
import com.epam.preprod.patsera.shop.service.captcha.CaptchaService;
import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private CaptchaService captchaService;
    private CaptchaStorageStrategy captchaStorage;

    @Override
    public void init() throws ServletException {
        captchaService = (CaptchaService) getServletContext().getAttribute(Constant.Captcha.CAPTCHA_SERVICE);
        captchaStorage = (CaptchaStorageStrategy) getServletContext().getAttribute(Constant.Captcha.CAPTCHA_STORAGE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String captchaId = getCaptchaId(req);
        resp.setContentType("image/jpg");
        captchaStorage.write(req, resp, captchaId);
        Captcha captcha = captchaService.generateAndWriteCaptchaToOutputStream(resp.getOutputStream(), captchaId);
        captchaService.add(captchaId, captcha);
    }

    private String getCaptchaId(HttpServletRequest request) {
        return (String) request.getServletContext().getAttribute(Constant.Captcha.CAPTCHA_ID);
    }
}
