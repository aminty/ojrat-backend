package com.amin.ojrat.controller;
import com.amin.ojrat.dto.entity.admin.request.AdminCreationDto;
import com.amin.ojrat.exception.AdminCreationException;
import com.amin.ojrat.exception.UserExistsException;
import com.amin.ojrat.service.ServiceRegistry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/management")
public class AdminController {

    private final ServiceRegistry serviceRegistry;


    @Autowired
    public AdminController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createNewAdminWithValidation(@Valid @RequestBody AdminCreationDto param) throws Exception  {
        if (param==null)
                throw new AdminCreationException("server receive null object!");
        if (!serviceRegistry.getAdminService().isExistsAdminByValue(param)) {
            serviceRegistry.getAdminService().saveAdmin(param);
            return new ResponseEntity<>("Admin created successfully.", HttpStatus.CREATED);
        }
        else {
            throw new UserExistsException("one of this params is taken by another user!");
        }
    }


}
