package com.epam.preprod.patsera.shop.web.tag;

import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.UUID;

public class InsertCaptchaTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext) getJspContext();
        out.println(Constant.HTML.IMG_SRC + pageContext.getServletContext().getContextPath() + Constant.HTML.CAPTCHA +
                Constant.HTML.PLACEHOLDER + Constant.HTML.ERROR_SPAN);
        String id = generateCaptchaId();
        pageContext.getServletContext().setAttribute(Constant.Captcha.CAPTCHA_ID, id);
        out.println(Constant.HTML.getHiddenField(id));
    }

    private String generateCaptchaId() {
        return UUID.randomUUID().toString();
    }
}
