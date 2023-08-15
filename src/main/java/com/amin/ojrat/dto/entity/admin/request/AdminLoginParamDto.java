package com.amin.ojrat.dto.entity.admin.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AdminLoginParamDto {


    @NotBlank(message = "identity value should not be empty")
    private final String username;


    @NotBlank(message = "password should not be empty")
    private final String password;


    public AdminLoginParamDto(String username, String password) {
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
