package com.amin.ojrat.repository;

import com.amin.ojrat.dto.entity.user.request.UserLoginParamDto;
import com.amin.ojrat.dto.entity.user.response.UserLoginResultDto;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.exception.LoginAuthenticationException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findUserByPhoneNumber(String phone);

}
