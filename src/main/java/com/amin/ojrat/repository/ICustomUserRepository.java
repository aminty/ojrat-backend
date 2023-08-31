package com.amin.ojrat.repository;

import com.amin.ojrat.dto.entity.user.request.UserLoginDtoParam;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.exception.LoginAuthenticationException;

public interface ICustomUserRepository {

    User login(UserLoginDtoParam param) throws LoginAuthenticationException;


    boolean isUserExistsByValue(String natCode, String email, String phoneNumber);
}
