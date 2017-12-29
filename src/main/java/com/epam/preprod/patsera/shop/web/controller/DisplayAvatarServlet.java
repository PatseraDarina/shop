package com.epam.preprod.patsera.shop.web.controller;

import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/displayAvatar")
public class DisplayAvatarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String avatar = (String) session.getAttribute(Constant.AVATAR);

        response.setContentType(Constant.IMAGE_JPEG);

        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(avatar));
             BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream())) {
                int ch;
                while((ch=bin.read()) != -1)
                {
                    bout.write(ch);
                }
        }
    }
}
