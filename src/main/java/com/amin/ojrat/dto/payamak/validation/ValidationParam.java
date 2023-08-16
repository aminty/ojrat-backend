package com.amin.ojrat.dto.payamak.validation;


public class ValidationParam {

    String phoneNumber;
    String value;

    public ValidationParam() {
    }

    public ValidationParam(String phoneNumber, String value) {
        this.phoneNumber = phoneNumber;
        this.value = value;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValidationParam{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
