package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.dto.mapper.AdminMapper;
import com.amin.ojrat.exception.AdminCreationException;
import com.amin.ojrat.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management")
public class AdminController {

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminController(AdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }

    @Operation(summary = "create new admin")
    public ResponseEntity<String> createNewAdmin(@RequestBody AdminParam param) throws AdminCreationException  {

        if (param==null)
                throw new AdminCreationException("server receive null object!");
        adminService.saveAdmin(param);

        return new ResponseEntity<>("Admin created successfully.", HttpStatus.CREATED);
    }


}
