package com.amin.ojrat.service;

public interface CodeValidationService {


    void doSendingCodeOperation(String phoneNumber);

    boolean isValidCode(String code,String phoneNumber);

    void setCodeInRedis(String code,String phoneNumber);

    String getCodeBaseOnPhoneFromRedis(String code);

    String codeGenerator();

    boolean sendCodeWithApi(String phoneNumber);


}
