package com.amin.ojrat.dto.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserParam {

    //@Schema(defaultValue = "firstName")
    @NotBlank(message = "firstName should not be blank!")
    String firstName;


    //@Schema(defaultValue = "lastName")
    @NotNull(message = "lastName should not be blank!")
    String lastName;


    //@Schema(defaultValue = "email")
    @NotBlank(message = "email should not be blank!")
    String email;


    //@Schema(defaultValue = "password")
    @NotBlank(message = "password should not be blank!")
    String password;


    //@Schema(defaultValue = "nationalCode")
    @NotBlank(message = "nationalCode should not be blank!")
    String nationalCode;


    //@Schema(defaultValue = "address")
    @NotBlank(message = "address should not be blank!")
    String address;

}
