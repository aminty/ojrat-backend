package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.entity.branch.request.BranchInfoModificationDtoParam;
import com.amin.ojrat.dto.entity.branch.request.ChangeDiscountDtoParam;
import com.amin.ojrat.dto.entity.product.request.ProductCreationDtoParam;
import com.amin.ojrat.dto.entity.product.request.ProductModificationDtoParam;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.UniqueNameException;
import com.amin.ojrat.service.ServiceRegistry;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final ServiceRegistry serviceRegistry;

    public BranchController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Operation(summary = "if branch was found and is active {is fully registered with valid data} -> add product to it")
    @PostMapping("/add-product")
    public ResponseEntity<String> addProductToBranch(@Valid @RequestBody ProductCreationDtoParam param) throws Exception {
        serviceRegistry.getBranchService().saveProductToBranch(param);
        return new ResponseEntity<>("product saved successfully", HttpStatus.OK);
    }

    @Operation(summary = "{edit} product info if the product is found.")
    @PostMapping("edit-product")
    public ResponseEntity<String> editProduct(@Valid @RequestBody ProductModificationDtoParam param) throws Exception {
        serviceRegistry.getBranchService().editProduct(param);
        return new ResponseEntity<>("product updated successfully", HttpStatus.OK);
    }

    @Operation(summary = "{delete} product if the product is found.")
    @GetMapping("remove-product/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable Long id) throws Exception {
        serviceRegistry.getBranchService().removeProduct(id);
        return new ResponseEntity<>("product removed successfully", HttpStatus.OK);
    }

    @Operation(summary = "{edit} branch info if the branch is exists.")
    @PostMapping("edit-branch_info")
    public ResponseEntity<String> updateBranchInfo(@Valid @RequestBody BranchInfoModificationDtoParam param)
            throws UniqueNameException {
        serviceRegistry.getBranchService().editBranchEditInfo(param);
        return new ResponseEntity<>("branch info updated successfully!",HttpStatus.OK);
    }
    @Operation(summary = "get all given requests to branch by given id")
    @GetMapping("/request/get-all/{branchId}")
    public ResponseEntity<List<ExpBrBasicDtoResult>> getAllRequests(@PathVariable Long branchId){
        List<ExpBrBasicDtoResult> resultList = serviceRegistry.getBranchService().getAllJoinRequest(branchId);
        if (resultList.isEmpty()) return new ResponseEntity<>(resultList,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }
    @Operation(summary = "delete given request by given request id -> just delete not accepted requests.")
    @GetMapping("/request/delete/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long requestId) throws DeletionException {
        serviceRegistry.getBranchService().deleteRequest(requestId);
        return new ResponseEntity<>("request was deleted successfully!",HttpStatus.OK);

    }

    @Operation(summary = "accept given request by given request id -> just approve not accepted requests.")
    @PostMapping("/request/accept")
    public ResponseEntity<String> deleteRequest(@Valid @RequestBody ExpBrActivationDtoParam param)
            throws ChangeStatusException {
        serviceRegistry.getBranchService().changeRequestStatus(param);
        return new ResponseEntity<>("request was updated successfully!",HttpStatus.OK);

    }
    @Operation(summary = "remove joined expert from branch and delete {discount, approved request, relation}")
    @PostMapping("/remove-joined-expert")
    public ResponseEntity<String> removeJoinedExpert(@Valid @RequestBody ExpBrDtoParam param)
            throws RelationNotFoundException {
        serviceRegistry.getBranchService().removeExpertFromBranch(param.getExpertId(), param.getBranchId());
        return new ResponseEntity<>("user removed from branch",HttpStatus.OK);

    }
    @Operation(summary = "change discount for expert if exists any discount record.")
    @PostMapping("/change-discount")
    public ResponseEntity<String> changeDiscountPercent(@Valid @RequestBody ChangeDiscountDtoParam param){
        serviceRegistry.getBranchService().changeDiscountPercent(param);
        return new ResponseEntity<>("discount changed successfully",HttpStatus.OK);
    }



}
