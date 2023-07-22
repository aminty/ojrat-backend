package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.dto.entity.product.ProductParam;
import com.amin.ojrat.dto.mapper.AdminMapper;
import com.amin.ojrat.exception.AdminCreationException;
import com.amin.ojrat.exception.UserExistsException;
import com.amin.ojrat.service.AdminService;
import jakarta.validation.Valid;
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

    //@Operation(summary = "create new admin")
    @PostMapping("/create-admin")
    public ResponseEntity<String> createNewAdminWithValidation(@Valid @RequestBody AdminParam param) throws AdminCreationException, UserExistsException {

        if (param==null)
                throw new AdminCreationException("server receive null object!");
        if (!adminService.isExistsAdminByValue(param)) {
            adminService.saveAdmin(param);

            return new ResponseEntity<>("Admin created successfully.", HttpStatus.CREATED);
        }
        else {
            throw new UserExistsException("one of this params is taken by another user!");
        }
    }

//    public ResponseEntity<String> createNewProduct(@Valid @RequestBody ProductParam param ){
//
//    }


}
