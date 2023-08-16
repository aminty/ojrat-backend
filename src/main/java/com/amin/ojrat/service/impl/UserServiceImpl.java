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

        User userWhoWantLogin = daoRepositories.getCustomUserRepository().login(param);
        if (userWhoWantLogin.isIsDeleted()) {
            throw new EntityNotFoundException("this account was suspended");
        }
        if (!param.getPassword().trim().equals(userWhoWantLogin.getPassword())) {

            throw new LoginAuthenticationException("username or password is not correct!");

        }
        if (!userWhoWantLogin.isActive()) {

            throw new LoginAuthenticationException("this user is not active.");
        }
        return userMapper.userToUserLoginResultDto(userWhoWantLogin);

    }

    @Override
    public SendSmsResult resetPassword(String phone) {

        //todo: should check  if reqId is not null, send relative message
        String passage = "کلمه عبور شما:  ";
        try {
            User userByPhoneNumber = daoRepositories.getUserRepository().findUserByPhoneNumber(phone);
            SendSmsResult result =
                    codeValidationService.sendPassword(
                            userByPhoneNumber.getPhoneNumber(),
                            passage.concat(userByPhoneNumber.getPassword())
                    );
            result.setStatus("رمز عبور به این شماره ارسال شد. " + maskPhone(phone));
            return result;
        } catch (Exception e) {
            throw new EntityNotFoundException("user not found by :" + phone);

        }
    }

    @Override
    public String maskEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex == -1) {
            return email;
        }
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        if (username.length() < 3) {
            return email;
        }

        String maskedUsername = username.substring(0, 3) + "****";

        return maskedUsername + domain;
    }

    @Override
    public String maskPhone(String phone) {
        int firstFrom = 0;
        int firstEnd = 4;
        int secondFrom = 7;
        int secondEnd = 11;
        String result = phone.substring(firstFrom, firstEnd) + "****" + phone.substring(secondFrom, secondEnd);
        System.out.println(result);
        return result;
    }
}
