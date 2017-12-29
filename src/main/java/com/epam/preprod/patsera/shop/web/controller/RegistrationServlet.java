package com.epam.preprod.patsera.shop.web.controller;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.dto.CaptchaDto;
import com.epam.preprod.patsera.shop.dto.RegistrationFormDto;
import com.epam.preprod.patsera.shop.exception.AuthorizationException;
import com.epam.preprod.patsera.shop.exception.UserExistException;
import com.epam.preprod.patsera.shop.service.captcha.CaptchaService;
import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.service.user.UserService;
import com.epam.preprod.patsera.shop.util.Constant;
import com.epam.preprod.patsera.shop.web.validator.RegistrationValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@WebServlet("/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private UserService userService;
    private RegistrationValidator registrationValidator;
    private CaptchaService captchaService;
    private CaptchaStorageStrategy captchaStorage;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(Constant.Attribute.USER_SERVICE);
        registrationValidator = (RegistrationValidator) getServletContext().getAttribute(Constant.Attribute.REGISTRATION_VALIDATOR);
        captchaService = (CaptchaService) getServletContext().getAttribute(Constant.Captcha.CAPTCHA_SERVICE);
        captchaStorage = (CaptchaStorageStrategy) getServletContext().getAttribute(Constant.Captcha.CAPTCHA_STORAGE);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        RegistrationFormDto registrationFormDto = readForm(request);
        String avatar = getAvatar(registrationFormDto);
        Map<String, String> errors = registrationValidator.validate(registrationFormDto, captchaService);
        User user = registrationFormDto.createUser();
        if (errors.isEmpty()) {
            try {
                userService.register(registrationFormDto);
                session.setAttribute(Constant.AVATAR, avatar);
            } catch (UserExistException e) {
                errors.put(Constant.Attribute.USER_EXIST, e.getMessage());
                LOGGER.warn(e.getMessage(), e);
            } catch (AuthorizationException e) {
                errors.put(Constant.Error.REGISTRATION_ERROR, e.getMessage());
                LOGGER.warn(e.getMessage(), e);
            }
        }
        if (errors.isEmpty()) {
            session.setAttribute(Constant.Bean.USER, user);
            response.sendRedirect(Constant.JSP.CABINET_JSP);
        } else {
            resetPassword(user);
            session.setAttribute(Constant.Bean.USER, user);
            session.setAttribute(Constant.Attribute.USER_REGISTRATION_ERRORS, errors);
            request.getRequestDispatcher(Constant.JSP.REGISTRATION_JSP).include(request, response);
            errors.clear();
        }
    }

    private RegistrationFormDto readForm(HttpServletRequest request) {
        RegistrationFormDto registrationFormDto = new RegistrationFormDto();
        registrationFormDto.setFirstName(request.getParameter(Constant.Attribute.FIRST_NAME));
        registrationFormDto.setLastName(request.getParameter(Constant.Attribute.LAST_NAME));
        registrationFormDto.setEmail(request.getParameter(Constant.Attribute.EMAIL));
        registrationFormDto.setPassword(request.getParameter(Constant.Attribute.PASSWORD));
        registrationFormDto.setConfirmPassword(request.getParameter(Constant.Attribute.CONFIRM_PASSWORD));
        registrationFormDto.setGetNews(request.getParameter(Constant.Attribute.NEWS) != null);
        String captchaString = request.getParameter(Constant.Captcha.GUESS_CAPTCHA);
        String captchaId = captchaStorage.read(request);
        registrationFormDto.setCaptchaDto(new CaptchaDto(captchaString, captchaId));
        try {
            registrationFormDto.setImage(request.getPart(Constant.AVATAR));
        } catch (IOException | ServletException e) {
            LOGGER.warn(Constant.Error.FAIL_IMAGE + e.getMessage());
        }
        return registrationFormDto;
    }

    private String getAvatar(RegistrationFormDto registrationFormDto) {
        String avatar;
        if (Files.exists(Paths.get(Constant.PATH_AVATARS + registrationFormDto.getEmail() + Constant.JPG))) {
            avatar = Constant.PATH_AVATARS + registrationFormDto.getEmail() + Constant.JPG;
        } else {
            avatar = Constant.DEFAULT_AVATAR;
        }
        return avatar;
    }

    private void resetPassword(User user) {
        user.setPassword(Constant.EMPTY);
    }

}
