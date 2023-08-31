package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.user.request.UserLoginDtoParam;
import com.amin.ojrat.dto.entity.user.response.UserLoginDtoResult;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.exception.LoginAuthenticationException;
import com.amin.ojrat.service.ServiceRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ServiceRegistry serviceRegistry;

    public UserController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginDtoResult>
    loginUser(@Valid @RequestBody UserLoginDtoParam param) throws LoginAuthenticationException {

        //todo :should return session inside of UserLoginParamDto
        UserLoginDtoResult result = serviceRegistry.getUserService().login(param);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/account/forget-pass/{phone}")
    public ResponseEntity<SendSmsResult> passwordRecovery(@PathVariable String phone){
        SendSmsResult result = serviceRegistry.getUserService().getForgottenPassword(phone);
        return new ResponseEntity<SendSmsResult>(result,HttpStatus.OK);

    }
}
