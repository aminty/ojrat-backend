package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.entity.branch.response.BasicBranchDtoResult;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDtoParam;
import com.amin.ojrat.dto.entity.product.response.BasicProductDtoResult;
import com.amin.ojrat.dto.entity.ticket.request.ChangeTicketStatusDtoParam;
import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.PermissionDeniedException;
import com.amin.ojrat.service.ServiceRegistry;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {

    private final ServiceRegistry serviceRegistry;

    public ExpertController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }


    @Operation(summary = "create and save new expert with checking unique param")
    @PostMapping("/create-expert")
    public ResponseEntity<String> createNewExpertWithValidation
            (@Valid @RequestBody ExpertCreationDtoParam param) throws Exception {
        if (param == null)
            throw new CreationException("server receive null object!");
        serviceRegistry.getExpertService().saveExpert(param);
        return new ResponseEntity<>("expert created successfully.", HttpStatus.CREATED);

    }


    @Operation(summary = "make join request by checking -> " +
            "if this expert has {not} any request to {this branch} or his requests is {lower than 5}")
    @PostMapping("/join-request")
    public ResponseEntity<String> makeJoinRequest
            (@Valid @RequestBody ExpBrDtoParam param) throws Exception {
        serviceRegistry.getExpertService().makeJoinRequest(param);
        return new ResponseEntity<>("درخواست انجام شد.", HttpStatus.OK);
    }

    @Operation(summary = "get all created requests by expert ")
    @GetMapping("/get-all-requests/{expertId}")
    public ResponseEntity<List<ExpBrBasicDtoResult>> getAllRequests(@PathVariable Long expertId) {
        List<ExpBrBasicDtoResult> resultList = serviceRegistry.getExpertService().getAllJoinRequest(expertId);
        if (resultList.isEmpty()) return new ResponseEntity<>(resultList, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Operation(summary = "get all active branch for choosing for request")
    @GetMapping("/get-active-branch")
    public ResponseEntity<Page<BasicBranchDtoResult>> getActiveBranches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BasicBranchDtoResult> activeBranches = serviceRegistry.getExpertService().getAllAllowedBranch(pageable);
        if (activeBranches.isEmpty()) return new ResponseEntity<>(activeBranches, HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(activeBranches);
    }

    @Operation(summary = "get all branch that expert has been join to them.")
    @GetMapping("/available-branch/{expertId}")
    public ResponseEntity<Page<BasicBranchDtoResult>> getBranchesOfExpert(@PathVariable Long expertId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BasicBranchDtoResult> availableBranch =
                serviceRegistry.getExpertService().findAvailableBranch(expertId, pageable);
        if (availableBranch.isEmpty()) return new ResponseEntity<>(availableBranch, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(availableBranch, HttpStatus.OK);

    }

    @Operation(summary = "get all product with given branch id ")
    @GetMapping("/get-products/{branchId}")
    public ResponseEntity<Page<BasicProductDtoResult>> getProductOfBranch(@PathVariable Long branchId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BasicProductDtoResult> availableProduct =
                serviceRegistry
                        .getBranchService()
                        .findAvailableProduct(branchId, pageable);
        if (availableProduct.isEmpty())
            return new ResponseEntity<>(availableProduct, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(availableProduct, HttpStatus.OK);

    }
    @Operation(summary = "create new ticket")
    @PostMapping("/ticket/create")
    public ResponseEntity<String> createNewTicket(@Valid @RequestBody TicketCreationDtoParam param)
            throws CreationException {
        serviceRegistry.getTicketService().createTicket(param);
        return new ResponseEntity<>("ticket created successfully", HttpStatus.OK);
    }

    @Operation(summary = "close the ticket of expert by it self")
    @PostMapping("/ticket/change-status")
    public ResponseEntity<String> changeStatusToClose(@Valid @RequestBody ChangeTicketStatusDtoParam param)
            throws PermissionDeniedException {
        serviceRegistry.getTicketService().closeTicketByExpert(param.getTicketId(), param.getExpertId());
        return new ResponseEntity<>("ticket closed successfully", HttpStatus.OK);
    }
}
