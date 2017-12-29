package com.epam.preprod.patsera.shop.web.initializer;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.repository.captcha.CaptchaRepository;
import com.epam.preprod.patsera.shop.repository.captcha.impl.CaptchaRepositoryImpl;
import com.epam.preprod.patsera.shop.repository.parser.ResultSetParser;
import com.epam.preprod.patsera.shop.repository.parser.impl.UserParser;
import com.epam.preprod.patsera.shop.repository.transaction.TransactionManager;
import com.epam.preprod.patsera.shop.repository.user.UserRepository;
import com.epam.preprod.patsera.shop.repository.user.impl.UserRepositoryImpl;
import com.epam.preprod.patsera.shop.service.captcha.CaptchaService;
import com.epam.preprod.patsera.shop.service.captcha.strategy.CaptchaStorageStrategy;
import com.epam.preprod.patsera.shop.service.captcha.strategy.impl.CaptchaStorageCookieStrategyImpl;
import com.epam.preprod.patsera.shop.service.captcha.strategy.impl.CaptchaStorageHiddenStrategyImpl;
import com.epam.preprod.patsera.shop.service.captcha.strategy.impl.CaptchaStorageSessionStrategyImpl;
import com.epam.preprod.patsera.shop.service.user.UserService;
import com.epam.preprod.patsera.shop.util.Constant;
import com.epam.preprod.patsera.shop.web.validator.RegistrationValidator;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextInitializer implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextInitializer.class);

    private ServletContext servletContext;
    private long captchaLiveTime;
    private Map<String, CaptchaStorageStrategy> mapStorage = new HashMap<>();
    private DataSource dataSource;
    private TransactionManager transactionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        initDataSource();
        initTransactionManager();
        initCaptchaStorage(sce);
        initServices();
        initValidator();
    }

    private void initServices() {
        ResultSetParser<User> userParser = new UserParser();
        UserRepository userRepository = new UserRepositoryImpl(userParser);
        UserService userService = new UserService(transactionManager, userRepository);
        CaptchaRepository captchaRepository = new CaptchaRepositoryImpl();
        CaptchaService captchaService = new CaptchaService(captchaRepository, captchaLiveTime);

        servletContext.setAttribute(Constant.Attribute.USER_REPOSITORY, userRepository);
        servletContext.setAttribute(Constant.Attribute.USER_SERVICE, userService);
        servletContext.setAttribute(Constant.Captcha.CAPTCHA_SERVICE, captchaService);
    }

    private void initTransactionManager() {
        transactionManager = new TransactionManager(dataSource);
    }

    private void initDataSource() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/shop");
        } catch (NamingException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new RuntimeException("Unable to instantiate dataSource: ", e);
        }
    }

    private void initCaptchaStorage(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String captcha = servletContext.getInitParameter(Constant.Captcha.CAPTCHA_STRATEGY);

        captchaLiveTime = Long.parseLong(servletContext.getInitParameter(Constant.Captcha.CAPTCHA_LIVE_TIME));
        mapStorage.put(Constant.Captcha.SESSION_STORAGE, new CaptchaStorageSessionStrategyImpl());
        mapStorage.put(Constant.Captcha.COOKIE_STORAGE, new CaptchaStorageCookieStrategyImpl());
        mapStorage.put(Constant.Captcha.HIDDEN_STORAGE, new CaptchaStorageHiddenStrategyImpl());
        CaptchaStorageStrategy captchaStorage = mapStorage.get(captcha);

        servletContext.setAttribute(Constant.Captcha.CAPTCHA_STORAGE, captchaStorage);
    }

    private void initValidator() {
        RegistrationValidator registrationValidator = new RegistrationValidator();
        servletContext.setAttribute(Constant.Attribute.REGISTRATION_VALIDATOR, registrationValidator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
