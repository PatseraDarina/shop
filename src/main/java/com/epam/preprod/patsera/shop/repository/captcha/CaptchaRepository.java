package com.epam.preprod.patsera.shop.repository.captcha;

import com.epam.preprod.patsera.shop.bean.Captcha;

import java.util.List;
import java.util.Map;

public interface CaptchaRepository {
    void add(String id, Captcha captcha);

    Captcha get(String id);

    boolean contains(String id);

    List<Captcha> getAll();
}
