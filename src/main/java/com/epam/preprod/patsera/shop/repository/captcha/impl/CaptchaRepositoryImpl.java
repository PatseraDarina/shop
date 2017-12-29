package com.epam.preprod.patsera.shop.repository.captcha.impl;

import com.epam.preprod.patsera.shop.bean.Captcha;
import com.epam.preprod.patsera.shop.repository.captcha.CaptchaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptchaRepositoryImpl implements CaptchaRepository {

    private final Map<String, Captcha> captchaMap = new HashMap<>();

    @Override
    public void add(String id, Captcha captcha) {
        synchronized (captchaMap) {
            this.captchaMap.put(id, captcha);
        }
    }

    @Override
    public Captcha get(String id) {
        removeOutdatedCaptcha();
        return captchaMap.get(id);
    }

    @Override
    public boolean contains(String id) {
        return get(id) != null;
    }


    @Override
    public List<Captcha> getAll() {
        return new ArrayList<>(captchaMap.values());
    }

    private void removeOutdatedCaptcha() {
        synchronized (captchaMap) {
            captchaMap.values().removeIf(captcha -> captcha.getCurrentTime() < System.currentTimeMillis());
        }
    }
}
