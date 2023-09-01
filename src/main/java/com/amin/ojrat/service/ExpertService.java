package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.entity.branch.response.BasicBranchDtoResult;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDtoParam;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpertService {

    void saveExpert(ExpertCreationDtoParam param) throws  UserExistsException;

    void updateExpert(Expert expert);

    boolean isExistsExpertByValue(ExpertCreationDtoParam param);

    void makeJoinRequest(ExpBrDtoParam param) throws NotFullyRegisteredException, RequestLimitExceededException, LicenseStatusException, Exception;

    boolean isExpertExistsInBranch(Long userId, Branch foundBranch);

    List<ExpBrBasicDtoResult> getAllJoinRequest(Long expertId);

    Page<BasicBranchDtoResult> getAllAllowedBranch(Pageable pageable);

    Expert findExpertById(Long id);

    boolean isExistsById(Long expertId);

    Page<BasicBranchDtoResult> findAvailableBranch(Long expertId, Pageable pageable);

}
