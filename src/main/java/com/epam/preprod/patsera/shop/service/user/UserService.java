package com.epam.preprod.patsera.shop.service.user;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.dto.LoginFormDto;
import com.epam.preprod.patsera.shop.dto.RegistrationFormDto;
import com.epam.preprod.patsera.shop.exception.AuthorizationException;
import com.epam.preprod.patsera.shop.exception.SavingImageException;
import com.epam.preprod.patsera.shop.exception.TransactionInterruptedException;
import com.epam.preprod.patsera.shop.exception.UserExistException;
import com.epam.preprod.patsera.shop.repository.transaction.TransactionManager;
import com.epam.preprod.patsera.shop.repository.user.UserRepository;
import com.epam.preprod.patsera.shop.repository.util.ImageUploader;
import com.epam.preprod.patsera.shop.util.Constant;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;
    private TransactionManager transactionManager;

    public UserService(TransactionManager transactionManager, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }

    public Optional<User> getByEmail(String email) throws TransactionInterruptedException {
        return Optional.ofNullable(transactionManager.doTransaction(connection -> userRepository.getByEmail(connection, email)));
    }

    private boolean isExist(String email) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection -> userRepository.getByEmail(connection, email)) != null;
    }

    private void add(User user) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            userRepository.create(connection, user);
            return null;
        });
    }

    public User login(LoginFormDto loginFormDto) throws AuthorizationException {
        Optional<User> user = getUserByLoginData(loginFormDto);
        if (!user.isPresent()) {
            throw new AuthorizationException(Constant.Error.LOGIN_DATA_ERROR);
        }
        return user.get();
    }

    private Optional<User> getUserByLoginData(LoginFormDto loginFormDto) throws AuthorizationException {
        try {
            return Optional.ofNullable(transactionManager.doTransaction(connection -> userRepository.login(connection, loginFormDto.getEmail(), loginFormDto.getPassword())));
        } catch (TransactionInterruptedException e) {
            throw new AuthorizationException(Constant.Error.LOGIN_ERROR);
        }
    }

    public void register(RegistrationFormDto registrationFormDto) throws UserExistException, IOException, AuthorizationException {
        User user = registrationFormDto.createUser();
        try {
            if (!isExist(user.getEmail())) {
                saveAvatar(registrationFormDto.getImage(), user.getEmail());
                add(user);
            } else {
                throw new UserExistException(Constant.Error.USER_EXIST_ER);
            }
        } catch (TransactionInterruptedException e) {
            throw new UserExistException(Constant.Error.REGISTRATION_ERROR);
        }
    }

    private void saveAvatar(Part part, String email) throws IOException, AuthorizationException {
        ImageUploader imageUploader = new ImageUploader(part, email);
        try {
            imageUploader.saveImage();
        } catch (SavingImageException e) {
            throw new AuthorizationException(Constant.Error.FAIL_IMAGE);
        }
    }
}
