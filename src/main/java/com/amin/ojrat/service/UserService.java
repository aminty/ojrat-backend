package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.user.request.UserLoginDtoParam;
import com.amin.ojrat.dto.entity.user.response.UserLoginDtoResult;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.exception.LoginAuthenticationException;

public interface UserService {


     boolean isUserExistsByValue(String natCode, String email, String phoneNumber);

     UserLoginDtoResult login(UserLoginDtoParam param) throws LoginAuthenticationException;

     SendSmsResult getForgottenPassword(String phone);

     String maskEmail(String email);

     String maskPhone(String email);

     User findUserById(Long id);


}
