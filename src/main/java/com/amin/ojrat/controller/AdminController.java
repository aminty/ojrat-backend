package com.amin.ojrat.controller;

import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@AllArgsConstructor
public class AdminController {

    AdminService adminService;

    @PostMapping("/createAdmin")
    ResponseEntity<String> createNewAdmin(@RequestBody Admin admin){
        //todo : authenticate phone number with web service
        if (adminService.authenticateAndSave(admin))
            return  ResponseEntity.ok("ثبت نام موفقیت آمیز بود.");
        else
            return new ResponseEntity<>("", HttpStatus.NOT_ACCEPTABLE);

    }

}
