package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.entity.branch.request.BranchInfoModificationDto;
import com.amin.ojrat.dto.entity.product.request.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.request.ProductModificationDto;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.UniqueNameException;
import com.amin.ojrat.service.ServiceRegistry;
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
    public ResponseEntity<String> updateBranchInfo(@Valid @RequestBody BranchInfoModificationDto param)
            throws UniqueNameException {
        serviceRegistry.getBranchService().editBranchEditInfo(param);
        return new ResponseEntity<>("branch info updated successfully!",HttpStatus.OK);
    }

    @GetMapping("/request/get-all/{branchId}")
    public ResponseEntity<List<ExpBrBasicResult>> getAllRequests(@PathVariable Long branchId){
        List<ExpBrBasicResult> resultList = serviceRegistry.getBranchService().getAllJoinRequest(branchId);
        if (resultList.isEmpty()) return new ResponseEntity<>(resultList,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }

    @GetMapping("/request/delete/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long requestId) throws DeletionException {
        serviceRegistry.getBranchService().deleteRequest(requestId);
        return new ResponseEntity<>("request was deleted successfully!",HttpStatus.OK);

    }

    @PostMapping("/request/accept")
    public ResponseEntity<String> deleteRequest(@Valid @RequestBody ExpBrActivationParam param)
            throws ChangeStatusException {
        serviceRegistry.getBranchService().changeRequestStatus(param);
        return new ResponseEntity<>("request was updated successfully!",HttpStatus.OK);

    }

    @PostMapping("/remove-joined-expert")
    public ResponseEntity<String> removeJoinedExpert(@Valid @RequestBody ExpBrParam param)
            throws RelationNotFoundException {
        serviceRegistry.getBranchService().removeExpertFromBranch(param.getExpertId(), param.getBranchId());
        return new ResponseEntity<>("user removed from branch",HttpStatus.OK);

    }

}
