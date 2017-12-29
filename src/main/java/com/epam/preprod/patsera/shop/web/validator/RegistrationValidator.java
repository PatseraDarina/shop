package com.epam.preprod.patsera.shop.web.validator;

import com.epam.preprod.patsera.shop.bean.Captcha;
import com.epam.preprod.patsera.shop.dto.RegistrationFormDto;
import com.epam.preprod.patsera.shop.service.captcha.CaptchaService;
import com.epam.preprod.patsera.shop.util.Constant;

import java.util.HashMap;
import java.util.Map;

import static com.epam.preprod.patsera.shop.util.Validator.*;

public class RegistrationValidator {

    private Map<String, String> mapError = new HashMap<>();

    public Map<String, String> validate(RegistrationFormDto registrationFormDto, CaptchaService captchaService) {
        validateFullName(registrationFormDto.getFirstName(), registrationFormDto.getLastName());
        validateEmail(registrationFormDto.getEmail());
        validatePassword(registrationFormDto.getPassword());
        validateCaptcha(registrationFormDto.getCaptchaDto().getString());

        Captcha expectedCaptcha = captchaService.get(registrationFormDto.getCaptchaDto().getId());
        checkCaptchaMatching(registrationFormDto.getCaptchaDto().getString(), expectedCaptcha);
        checkPasswordMatching(registrationFormDto.getPassword(), registrationFormDto.getConfirmPassword());
        return mapError;
    }

    private void validateFullName(String firstName, String lastName) {
        if (!isValidName(firstName)) {
            mapError.put(Constant.Attribute.FIRST_NAME, Constant.Error.INVALID_NAME);
        }
        if (!isValidName(lastName)) {
            mapError.put(Constant.Attribute.LAST_NAME, Constant.Error.INVALID_NAME);
        }
    }

    private void validateEmail(String email) {
        if (!isValidEmail(email)) {
            mapError.put(Constant.Attribute.EMAIL, Constant.Error.INVALID_EMAIL);
        }
    }

    private void validateCaptcha(String actualCaptcha) {
        if (!isValidCaptcha(actualCaptcha)) {
            mapError.put(Constant.Attribute.CAPTCHA, Constant.Error.INVALID_CAPTCHA_DATA);
        }
    }

    private void checkCaptchaMatching(String actualCaptcha, Captcha expectedCaptcha) {
        if (expectedCaptcha == null || !actualCaptcha.equalsIgnoreCase(expectedCaptcha.getCaptchaString())) {
            mapError.put(Constant.Attribute.CAPTCHA, Constant.Error.INVALID_CAPTCHA_DATA);
        }
    }

    private void validatePassword(String password) {
        if (!isValidPassword(password)) {
            mapError.put(Constant.Attribute.PASSWORD, Constant.Error.INVALID_PASSWORD);
        }
    }

    private void checkPasswordMatching(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            mapError.put(Constant.Attribute.CONFIRM_PASSWORD, Constant.Error.PASSWORD_MATCHING);
        }
    }
}
