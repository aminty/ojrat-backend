package com.amin.ojrat.controller;


import com.amin.ojrat.dto.payamak.send.SendSmsParam;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.validation.ValidationParam;
import com.amin.ojrat.service.CodeValidationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/validator")
public class CodeValidatorController {

    @Autowired
    private  CodeValidationService codeValidationService;

    @GetMapping("/validatePhone/{phoneNumber}")
    public ResponseEntity<SendSmsResult> validatePhone(@PathVariable String phoneNumber) {
        SendSmsResult sendSmsResult = codeValidationService.sendCodeWithApi(phoneNumber);
        return ResponseEntity.ok(sendSmsResult);
    }

    @PostMapping("/validateCode")
    public boolean validateCode(@RequestBody ValidationParam param) {
         return codeValidationService.isValidCode(param.getCode(), param.getPhoneNumber());
    }

    @GetMapping("/getAllValue")
    public List<ValidationParam> getAllCacheValue(){

        return codeValidationService.getAllCacheValue();

    }

}
