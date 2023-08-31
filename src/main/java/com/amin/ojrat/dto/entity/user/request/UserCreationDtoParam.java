package com.amin.ojrat.dto.entity.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserCreationDtoParam {

    @NotBlank(message = "firstName should not be blank!")
    private String firstName;


    //@Schema(defaultValue = "lastName")
    @NotBlank(message = "lastName should not be blank!")
    private String lastName;


    @NotBlank(message = "email should not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "email format is incorrect")
    private String email;


    //@Schema(defaultValue = "password")
    @NotBlank(message = "password should not be blank!")
    private String password;

    @NotBlank(message = "nationalCode should not be blank!")
    @Pattern(regexp = "^[0-9]\\d{9}$",message = "nationalCode format is incorrect")
    private String nationalCode;

    //@Schema(defaultValue = "address")
    @NotBlank(message = "address should not be blank!")
    private String address;

    @NotBlank(message = "phoneNumber should not be blank!")
    @Pattern(regexp = "^(\\+98|0098|98|0)?9\\d{9}$", message = "phoneNumber format is incorrect")
    private String phoneNumber;


    public UserCreationDtoParam() {
    }

    public UserCreationDtoParam(String firstName,
                                String lastName,
                                String email,
                                String password,
                                String nationalCode,
                                String address,
                                String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationalCode = nationalCode;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
