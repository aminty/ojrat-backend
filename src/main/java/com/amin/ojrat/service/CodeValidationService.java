package com.amin.ojrat.service;

import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.validation.ValidationParam;

import java.util.List;

public interface CodeValidationService {



    boolean isValidCode(String code,String phoneNumber);

    void setCodeInCache(String code,String phoneNumber);

    String getCodeBaseOnPhoneFromCache(String code);


    String codeGenerator();

    SendSmsResult sendCodeWithApi(String phoneNumber);

    List<ValidationParam> getAllCacheValue();



}
