package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Code;
import com.amin.ojrat.repository.CodeRepository;
import com.amin.ojrat.service.CodeValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
@AllArgsConstructor
public class CodeValidationServiceImpl implements CodeValidationService {
    private final CodeRepository codeRepository;
    @Override
    public void doSendingCodeOperation(String phoneNumber) {

        //todo: call codeGenerator
        String generatedCode = codeGenerator();

        //todo: send generated code to mobile  --> if code sent successfully
        if (sendCodeWithApi(phoneNumber))


            //todo:set code and mobile into redis
            setCodeInRedis(generatedCode, phoneNumber);


    }

    @Override
    public boolean isValidCode(String code, String phoneNumber) {
        //todo: get mobile with sending code to getCodeFromRedis(code)
        String fetchedCode = getCodeBaseOnPhoneFromRedis(phoneNumber);
        if (fetchedCode != null)
            if (code.equals(fetchedCode))
                return true;
        return false;
    }

    @Override
    public void setCodeInRedis(String code, String phoneNumber) {
        //todo: set code and phone to redis as key,value
        codeRepository.save(new Code(phoneNumber,code));

    }

    @Override
    public String getCodeBaseOnPhoneFromRedis(String code) {
        return null;
    }

    @Override
    public String codeGenerator() {
        Random random = new Random();
        // Generate a random 4-digit number
        int randomNumber = random.nextInt(9000) + 1000;
        // Convert the random number to a string
        return String.valueOf(randomNumber);

    }

    @Override
    public boolean sendCodeWithApi(String phoneNumber) {
        return false;
    }
}
