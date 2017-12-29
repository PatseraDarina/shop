import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.dto.RegistrationFormDto;
import com.epam.preprod.patsera.shop.exception.AuthorizationException;
import com.epam.preprod.patsera.shop.exception.TransactionInterruptedException;
import com.epam.preprod.patsera.shop.exception.UserExistException;
import com.epam.preprod.patsera.shop.repository.parser.ResultSetParser;
import com.epam.preprod.patsera.shop.repository.parser.impl.UserParser;
import com.epam.preprod.patsera.shop.repository.user.UserRepository;
import com.epam.preprod.patsera.shop.repository.user.impl.UserRepositoryImpl;
import com.epam.preprod.patsera.shop.service.captcha.CaptchaService;
import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.service.user.UserService;
import com.epam.preprod.patsera.shop.util.Constant;
import com.epam.preprod.patsera.shop.web.controller.RegistrationServlet;
import com.epam.preprod.patsera.shop.web.validator.RegistrationValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private CaptchaService captchaService;
    @Mock
    private CaptchaStorageStrategy captchaStorageStrategy;
    @Mock
    private RegistrationValidator registrationValidator;
    @Mock
    private UserService userService;

    private ResultSetParser<User> parser = new UserParser();
    private UserRepository userRepository = new UserRepositoryImpl(parser);
    private RegistrationServlet registrationServlet = new RegistrationServlet();


    @Before
    public void setUp() throws ServletException {
        when(servletContext.getAttribute(Constant.Attribute.USER_SERVICE)).thenReturn(userService);
        when(servletContext.getAttribute(Constant.Captcha.CAPTCHA_STORAGE)).thenReturn(captchaStorageStrategy);
        when(servletContext.getAttribute(Constant.Captcha.CAPTCHA_SERVICE)).thenReturn(captchaService);
        when(servletContext.getAttribute(Constant.Attribute.REGISTRATION_VALIDATOR)).thenReturn(registrationValidator);
        when(servletContext.getAttribute(Constant.Attribute.USER_REPOSITORY)).thenReturn(userRepository);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        registrationServlet.init(servletConfig);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("firstName")).thenReturn("John");
        when(request.getParameter("lastName")).thenReturn("Smith");
        when(request.getParameter("email")).thenReturn("smith@gmail.com");
        when(request.getParameter("password")).thenReturn("smith007");
        when(request.getParameter("confirmPassword")).thenReturn("smith007");
        when(request.getParameter("news")).thenReturn(String.valueOf(true));
        when(request.getParameter("guess-captcha")).thenReturn("1j2m3n");
    }

    @Test
    public void methodPostShouldBeExecuted() throws ServletException, IOException {
       registrationServlet.doPost(request, response);
    }

    @Test
    public void registrationShouldNotBeExecuted() throws ServletException, IOException, TransactionInterruptedException {
        String invalidEmail = "123132";

        when(request.getParameter("email")).thenReturn(invalidEmail);
        registrationServlet.doPost(request, response);
        assertFalse(userService.getByEmail(invalidEmail).isPresent());
    }
}