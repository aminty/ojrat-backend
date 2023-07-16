package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.payamak.send.SendSmsParam;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.validation.ValidationParam;
import com.amin.ojrat.service.CodeValidationService;
import com.amin.ojrat.service.external.MelliPayamakClient;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
public class CodeValidationServiceImpl implements CodeValidationService {

    private final Cache<String, String> codeCache;

    private final MelliPayamakClient melliPayamakClient;

    private final String MELLI_PHONE="50004001501261";

    @Autowired
    public CodeValidationServiceImpl(MelliPayamakClient melliPayamakClient) {
        this.melliPayamakClient = melliPayamakClient;
        codeCache = Caffeine.newBuilder()
                .expireAfterWrite(120, TimeUnit.SECONDS) // Default expiration time for records not specified in put method
                .maximumSize(100) // Change the maximum size as per your requirements
                .build();
    }




    @Override
    public boolean isValidCode(String code, String phoneNumber) {

        //todo: get mobile with sending code to getCodeFromRedis(code)
        String fetchedCode = getCodeBaseOnPhoneFromCache(phoneNumber);
        if (fetchedCode != null)
            return code.equals(fetchedCode);
        return false;
    }

    @Override
    public void setCodeInCache(String code, String phoneNumber) {
        codeCache.put(phoneNumber, code);

    }

    public String getCodeBaseOnPhoneFromCache(String phoneNumber) {
        if (phoneNumber != null) {
            return codeCache.getIfPresent(phoneNumber);
        }
        return null;
    }

    @Override
    public String codeGenerator() {

        Random random = new Random();
        String code;
        boolean isExistsValueInCache;

        do {
            // Generate a random 4-digit number
            code = String.valueOf(random.nextInt(9000) + 1000);

            isExistsValueInCache = codeCache.asMap().containsValue(code);
        } while (isExistsValueInCache);

        return code;
    }

    @Override
    public SendSmsResult sendCodeWithApi(String phoneNumber) {

        //todo: call codeGenerator
        String generatedCode = codeGenerator();
        //todo: send generated code to mobile

        //todo:set code and mobile into redis
        setCodeInCache(generatedCode, phoneNumber);

        return  melliPayamakClient.sendSms(new SendSmsParam(MELLI_PHONE, phoneNumber,  "کد تایید شماره همراه : " + generatedCode));
    }

    @Override
    public List<ValidationParam> getAllCacheValue() {
        ConcurrentMap<String, String> cacheMap = codeCache.asMap();
        List<ValidationParam> values=new ArrayList<>()  ;
        for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
            values.add(new ValidationParam(key,value));
        }
        return values;
    }
}
