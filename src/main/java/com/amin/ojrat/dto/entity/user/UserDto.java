package com.amin.ojrat.dto.entity.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



public class UserDto {

    @NotBlank(message = "id should not null!")
    private Long id;

    //@Schema(defaultValue = "firstName")
    @NotBlank(message = "firstName should not be blank!")
    private String firstName;


    //@Schema(defaultValue = "lastName")
    @NotBlank(message = "lastName should not be blank!")
    private String lastName;


    //@Schema(defaultValue = "email")
    @NotBlank(message = "email should not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
    private String email;


    //@Schema(defaultValue = "password")
    @NotBlank(message = "password should not be blank!")
    private String password;


    //@Schema(defaultValue = "nationalCode")
    @NotBlank(message = "nationalCode should not be blank!")
    @Pattern(regexp = "^[1-9]\\d{9}$")
    private String nationalCode;


    //@Schema(defaultValue = "address")
    @NotBlank(message = "address should not be blank!")
    private String address;

    @NotBlank(message = "phoneNumber should not be blank!")
    @Pattern(regexp = "^(\\+98|0098|98|0)?9\\d{9}$")
    private String phoneNumber;


    public UserDto() {
    }

    public UserDto(Long id,
                   String firstName,
                   String lastName,
                   String email,
                   String password,
                   String nationalCode,
                   String address,
                   String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationalCode = nationalCode;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
