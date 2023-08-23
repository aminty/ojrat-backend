package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.entity.ExpertBranchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExpertBranchService {


    void saveJoinRequest(ExpertBranchRequest expBr);

    Long countOfRequestByExpertId(Long expertId);

    List<ExpBrBasicResult> findAllRequestByExpertId(Long expertId);

    List<ExpBrBasicResult> findAllRequestByBranchId(Long branchId);

    boolean isExistRequestToBranchByThisUserId(ExpBrParam param);

    void deleteRequest(ExpBrParam param );

    ExpBrBasicResult changeRequestStatus(ExpBrActivationParam param);
}
