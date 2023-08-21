package com.amin.ojrat.controller;


import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.status.GetSmsStatusResult;
import com.amin.ojrat.dto.payamak.validation.ValidationParam;
import com.amin.ojrat.exception.TtlExpirationException;
import com.amin.ojrat.service.CodeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/validator")
public class CodeValidatorController {

    private final CodeValidationService codeValidationService;

    @Autowired
    public CodeValidatorController(CodeValidationService codeValidationService) {
        this.codeValidationService = codeValidationService;
    }

    @GetMapping("/validatePhone/{phoneNumber}")
    public ResponseEntity<GetSmsStatusResult> validatePhone(@PathVariable String phoneNumber) throws TtlExpirationException {
        GetSmsStatusResult result = codeValidationService.sendCodeWithApi(phoneNumber);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/validateCode")
    public boolean validateCode(@RequestBody ValidationParam param) {
         return codeValidationService.isValidCode(param.getValue(), param.getPhoneNumber());
    }

    @GetMapping("/getAllValue")
    public List<ValidationParam> getAllCacheValue(){

        return codeValidationService.getAllCacheValue();

    }

}
