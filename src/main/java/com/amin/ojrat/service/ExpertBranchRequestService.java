package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.entity.ExpertBranchRequest;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;

import java.util.List;

public interface ExpertBranchRequestService {


    void saveJoinRequest(ExpertBranchRequest expBr);

    Long countOfRequestByExpertId(Long expertId);

    List<ExpBrBasicResult> findAllRequestByExpertId(Long expertId);

    List<ExpBrBasicResult> findAllRequestByBranchId(Long branchId);

    boolean isExistRequest(Long requestId);

    boolean isExistRequestByExpertAndBranchIds(ExpBrParam param);

    void deleteRequest(Long requestId) throws DeletionException;

    ExpBrBasicResult changeRequestStatus(ExpBrActivationParam param) throws ChangeStatusException;
}
