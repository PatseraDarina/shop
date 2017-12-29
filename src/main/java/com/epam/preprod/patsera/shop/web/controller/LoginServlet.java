package com.epam.preprod.patsera.shop.web.controller;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.dto.LoginFormDto;
import com.epam.preprod.patsera.shop.exception.AuthorizationException;
import com.epam.preprod.patsera.shop.service.user.UserService;
import com.epam.preprod.patsera.shop.util.Constant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(Constant.Attribute.USER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        LoginFormDto loginFormDto = readForm(request);
        String avatar = getAvatar(loginFormDto);
        User user = null;

        try {
            session.setAttribute(Constant.AVATAR, avatar);
            user = userService.login(loginFormDto);
        } catch (AuthorizationException e) {
            resetPassword(loginFormDto);
            session.setAttribute(Constant.Attribute.USER_LOGIN_ERRORS, e.getMessage());
            session.setAttribute(Constant.Bean.LOGIN_DATA, loginFormDto);
            LOGGER.warn(e.getMessage(), e);
            request.getRequestDispatcher(Constant.JSP.INDEX_JSP).include(request, response);
        }
        if (Objects.nonNull(user)) {
            session.setAttribute(Constant.Bean.USER, user);
            response.sendRedirect(Constant.JSP.CABINET_JSP);
        }
    }

    private LoginFormDto readForm(HttpServletRequest request) {
        LoginFormDto loginFormDto = new LoginFormDto();
        loginFormDto.setEmail(request.getParameter(Constant.Attribute.EMAIL));
        loginFormDto.setPassword(request.getParameter(Constant.Attribute.PASSWORD));
        return loginFormDto;
    }

    private String getAvatar(LoginFormDto loginFormDto) {
        String avatar;
        if (Files.exists(Paths.get(Constant.PATH_AVATARS + loginFormDto.getEmail() + Constant.JPG))) {
            avatar = Constant.PATH_AVATARS + loginFormDto.getEmail() + Constant.JPG;
        } else {
            avatar = Constant.DEFAULT_AVATAR;
        }
        return avatar;
    }

    private void resetPassword(LoginFormDto loginFormDto) {
        loginFormDto.setPassword(Constant.EMPTY);
    }

}
