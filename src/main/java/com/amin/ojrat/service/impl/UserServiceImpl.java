package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.user.request.UserLoginParamDto;
import com.amin.ojrat.dto.entity.user.response.UserLoginResultDto;
import com.amin.ojrat.dto.mapper.UserMapper;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.exception.LoginAuthenticationException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.CodeValidationService;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

    private final DaoRepositories daoRepositories;
    private final UserMapper userMapper;
    private final CodeValidationService codeValidationService;

    @Autowired
    public UserServiceImpl(DaoRepositories daoRepositories,
                           UserMapper userMapper,
                           CodeValidationService codeValidationService) {
        this.daoRepositories = daoRepositories;
        this.userMapper = userMapper;
        this.codeValidationService = codeValidationService;
    }

    @Override
    public boolean isUserExistsByValue(String nationalCode, String email, String phoneNumber) {
        return daoRepositories.getCustomUserRepository().isUserExistsByValue(nationalCode, email, phoneNumber);
    }

    @Override
    public UserLoginResultDto login(UserLoginParamDto param) throws LoginAuthenticationException {
        User userWhoWantLogin = findUserForLogin(param.getUsername(), param.getPassword());
        checkUserLoginStatus(userWhoWantLogin);
        return userMapper.userToUserLoginResultDto(userWhoWantLogin);
    }

    @Override
    public SendSmsResult getForgottenPassword(String phone) {
        User userByPhoneNumber = findUserByPhoneNumber(phone);
        String message = "کلمه عبور شما: " + userByPhoneNumber.getPassword();
        SendSmsResult result = codeValidationService.sendPassword(userByPhoneNumber.getPhoneNumber(), message);
        result.setStatus("رمز عبور به این شماره ارسال شد. " + maskPhone(phone));
        return result;
    }

    @Override
    public String maskEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex < 3) {
            return email;
        }
        String maskedUsername = email.substring(0, 3) + "****";
        return maskedUsername + email.substring(atIndex);
    }

    @Override
    public String maskPhone(String phone) {
        return phone.substring(0, 4) + "****" + phone.substring(7, 11);
    }

    @Override
    public User findUserById(Long id) {
        return daoRepositories
                .getUserRepository()
                .findById(id).orElseThrow(()-> new EntityNotFoundException("user not found"));
    }

    // Helper methods

    private User findUserForLogin(String username, String password) throws LoginAuthenticationException {
        User userWhoWantLogin = daoRepositories.getCustomUserRepository().login(new UserLoginParamDto(username, password));
        if (userWhoWantLogin.isIsDeleted()) {
            throw new EntityNotFoundException("This account was suspended");
        }
        if (!password.trim().equals(userWhoWantLogin.getPassword())) {
            throw new LoginAuthenticationException("Username or password is not correct!");
        }
        if (!userWhoWantLogin.isActive()) {
            throw new LoginAuthenticationException("This user is not active.");
        }
        return userWhoWantLogin;
    }

    private void checkUserLoginStatus(User user) throws LoginAuthenticationException {
        if (user.isIsDeleted()) {
            throw new EntityNotFoundException("This account was suspended");
        }
        if (!user.isActive()) {
            throw new LoginAuthenticationException("This user is not active.");
        }
    }


    private User findUserByPhoneNumber(String phone) {
        return daoRepositories.getUserRepository().findUserByPhoneNumber(phone);
    }
}
