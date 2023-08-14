package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.branch.BranchInfoModificationDto;
import com.amin.ojrat.dto.entity.product.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.ProductModificationDto;
import com.amin.ojrat.service.ServiceRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final ServiceRegistry serviceRegistry;

    public BranchController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProductToBranch(@Valid @RequestBody ProductCreationDto param) throws Exception {
        serviceRegistry.getBranchService().saveProductToBranch(param);
        return new ResponseEntity<>("product saved successfully", HttpStatus.OK);
    }

    @PostMapping("edit-product")
    public ResponseEntity<String> editProduct(@Valid @RequestBody ProductModificationDto param) throws Exception {
        serviceRegistry.getBranchService().editProduct(param);
        return new ResponseEntity<>("product updated successfully", HttpStatus.OK);
    }

    @GetMapping("remove-product/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable Long id) throws Exception {
        serviceRegistry.getBranchService().removeProduct(id);
        return new ResponseEntity<>("product removed successfully", HttpStatus.OK);
    }

    @PostMapping("edit-branch_info")
    public ResponseEntity<String> updateBranchInfo(@Valid @RequestBody BranchInfoModificationDto param) {
        serviceRegistry.getBranchService().editBranchEditInfo(param);
        return new ResponseEntity<>("branch info updated successfully!",HttpStatus.OK);
    }
}
