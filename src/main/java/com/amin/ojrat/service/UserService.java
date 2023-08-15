package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.user.request.UserLoginParamDto;
import com.amin.ojrat.dto.entity.user.response.UserLoginResultDto;
import com.amin.ojrat.exception.LoginAuthenticationException;

public interface UserService {


     boolean isUserExistsByValue(String natCode, String email, String phoneNumber);


     UserLoginResultDto login(UserLoginParamDto param) throws LoginAuthenticationException;
}
