package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.entity.branch.response.BasicBranchDto;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.NotFullyRegisteredException;
import com.amin.ojrat.exception.RequestLimitExceededException;
import com.amin.ojrat.exception.UserExistsException;
import com.amin.ojrat.service.ServiceRegistry;
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



    @PostMapping("/create-expert")
    public ResponseEntity<String> createNewExpertWithValidation
            (@Valid @RequestBody ExpertCreationDto param) throws Exception  {
        if (param==null)
            throw new CreationException("server receive null object!");
        if (!serviceRegistry.getExpertService().isExistsExpertByValue(param)) {
            serviceRegistry.getExpertService().saveExpert(param);
            return new ResponseEntity<>("expert created successfully.", HttpStatus.CREATED);
        }
        else {
            throw new UserExistsException("one of this params is taken by another user!");
        }
    }



    @PostMapping("/join-request")
    public ResponseEntity<String> makeJoinRequest
            (@Valid @RequestBody ExpBrParam param) throws Exception {
        serviceRegistry.getExpertService().makeJoinRequest(param);
        return new ResponseEntity<String>("درخواست انجام شد.",HttpStatus.OK);
    }


    @GetMapping("/get-all-requests/{expertId}")
    public ResponseEntity<List<ExpBrBasicResult>> getAllRequests(@PathVariable Long expertId){
        List<ExpBrBasicResult> resultList = serviceRegistry.getExpertService().getAllJoinRequest(expertId);
        if (resultList.isEmpty()) return new ResponseEntity<>(resultList,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }

    @GetMapping("/get-active-branch")
    public ResponseEntity<Page<BasicBranchDto>> getActiveBranches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BasicBranchDto> activeBranches = serviceRegistry.getExpertService().getAllAllowedBranch(pageable);
        if (activeBranches.isEmpty()) return new ResponseEntity<>(activeBranches,HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(activeBranches);
    }


}
