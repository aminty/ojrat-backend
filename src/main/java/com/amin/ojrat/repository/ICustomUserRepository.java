package com.amin.ojrat.repository;

import com.amin.ojrat.dto.entity.user.request.UserLoginParamDto;
import com.amin.ojrat.dto.entity.user.response.UserLoginResultDto;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.exception.LoginAuthenticationException;

public interface ICustomUserRepository {

    User login(UserLoginParamDto param) throws LoginAuthenticationException;


    boolean isUserExistsByValue(String natCode, String email, String phoneNumber);
}
