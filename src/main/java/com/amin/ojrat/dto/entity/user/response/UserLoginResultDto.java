package com.amin.ojrat.dto.entity.user.response;

import java.time.LocalTime;

public class UserLoginResultDto {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String nationalCode;

    private final String address;

    private final String phoneNumber;

    private final LocalTime createdAt;


    public UserLoginResultDto(String firstName,
                              String lastName,
                              String email,
                              String nationalCode,
                              String address,
                              String phoneNumber,
                              LocalTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalCode = nationalCode;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }
}
