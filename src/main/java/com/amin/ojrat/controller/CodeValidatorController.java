package com.amin.ojrat.controller;


import com.amin.ojrat.service.CodeValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validator")
@AllArgsConstructor
public class CodeValidatorController {

    private final CodeValidationService codeValidationService;

    @PostMapping("/validatePhone")
    public ResponseEntity<String> validatePhone(@RequestParam String phoneNumber) {
        codeValidationService.doSendingCodeOperation(phoneNumber);
        return ResponseEntity.ok("کد تایید را ارسال نمایید");

    }

    @PostMapping("/validateCode")
    public boolean validateCode(@RequestParam String code,String phoneNumber) {
         return codeValidationService.isValidCode(code,phoneNumber);
    }


}
