package com.amin.ojrat.dto.entity.user.request;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDtoParam {


    @NotBlank(message = "identity value should not be empty")
    private final String username;


    @NotBlank(message = "password should not be empty")
    private final String password;


    public UserLoginDtoParam(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
