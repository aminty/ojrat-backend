package com.amin.ojrat.controller;


import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.status.GetSmsStatusResult;
import com.amin.ojrat.dto.payamak.validation.ValidationParam;
import com.amin.ojrat.exception.TtlExpirationException;
import com.amin.ojrat.service.CodeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Boolean> validateCode(@RequestBody ValidationParam param) {
        boolean isValidCode = codeValidationService.isValidCode(param.getValue(), param.getPhoneNumber());
        return new ResponseEntity<>(isValidCode, HttpStatus.OK );
    }

    @GetMapping("/getAllValue")
    public ResponseEntity<List<ValidationParam>> getAllCacheValue(){

        List<ValidationParam> allCacheValue = codeValidationService.getAllCacheValue();
        if (allCacheValue.isEmpty())
            return new ResponseEntity<>(allCacheValue,HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(allCacheValue,HttpStatus.OK);

    }

}
