package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.user.request.UserLoginParamDto;
import com.amin.ojrat.dto.entity.user.response.UserLoginResultDto;
import com.amin.ojrat.exception.LoginAuthenticationException;
import com.amin.ojrat.service.ServiceRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ServiceRegistry serviceRegistry;

    public UserController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResultDto>
    loginUser(@Valid @RequestBody UserLoginParamDto param) throws LoginAuthenticationException {

        //todo :should return session inside of UserLoginParamDto
        UserLoginResultDto result = serviceRegistry.getUserService().login(param);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
