package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.entity.ExpertBranchRequest;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;

import java.util.List;

public interface ExpertBranchRequestService {


    void saveJoinRequest(ExpertBranchRequest expBr);

    Long countOfRequestByExpertId(Long expertId);

    List<ExpBrBasicDtoResult> findAllRequestByExpertId(Long expertId);

    List<ExpBrBasicDtoResult> findAllRequestByBranchId(Long branchId);

    boolean isExistRequest(Long requestId);

    boolean isExistRequestByExpertAndBranchIds(ExpBrDtoParam param);

    void deleteRequest(Long requestId) throws DeletionException;

    void deleteRequestByIdsInsideExpertRelationDeletionInBranchService(Long expertId,Long branchID );

    ExpBrBasicDtoResult changeRequestStatus(ExpBrActivationDtoParam param) throws ChangeStatusException;
}
