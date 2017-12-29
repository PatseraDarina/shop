package com.epam.preprod.patsera.shop.web.validator;

import com.epam.preprod.patsera.shop.dto.CaptchaDto;
import com.epam.preprod.patsera.shop.dto.RegistrationFormDto;
import com.epam.preprod.patsera.shop.service.captcha.CaptchaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationValidatorTest {

    @Mock
    private CaptchaService captchaService;

    @Test
    public void methodShouldValidateRegistrationData() {

        //Given
        RegistrationValidator validator = new RegistrationValidator();
        int errorNumber = 4;

        Map<String, String> error;

        RegistrationFormDto registrationFormDto = new RegistrationFormDto();
        registrationFormDto.setFirstName("John12");
        registrationFormDto.setLastName("Smith*");
        registrationFormDto.setEmail("qqq@df");
        registrationFormDto.setGetNews(true);
        registrationFormDto.setCaptchaDto(new CaptchaDto("dfj12k", "1"));
        registrationFormDto.setPassword("password33");
        registrationFormDto.setConfirmPassword("password33");

        //When
        error = validator.validate(registrationFormDto, captchaService);

        //Then
        assertThat(error.size(), equalTo(errorNumber));
    }
}