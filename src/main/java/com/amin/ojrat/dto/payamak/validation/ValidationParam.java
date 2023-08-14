package com.amin.ojrat.dto.payamak.validation;

import lombok.*;
import lombok.experimental.FieldDefaults;


public class ValidationParam {

    String phoneNumber;
    String code;

    public ValidationParam() {
    }

    public ValidationParam(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ValidationParam{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
