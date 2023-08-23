package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.mapper.ExpertBranchMapper;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.ExpertBranchRequest;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.ExpertBranchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpertBranchServiceImpl implements ExpertBranchService {

    private final DaoRepositories daoRepositories;
    private final ExpertBranchMapper expertBranchMapper;


    @Autowired
    public ExpertBranchServiceImpl(DaoRepositories daoRepositories, ExpertBranchMapper expertBranchMapper) {
        this.daoRepositories = daoRepositories;
        this.expertBranchMapper = expertBranchMapper;
    }


    @Override
    @Transactional
    public void saveJoinRequest(ExpertBranchRequest expBr) {
        daoRepositories.getExpertBranchRepository().save(expBr);

    }

    @Override
    public Long countOfRequestByExpertId(Long expertId) {
        return daoRepositories.getExpertBranchRepository().countByExpert_Id(expertId);
    }

    @Override
    public List<ExpBrBasicResult> findAllRequestByExpertId(Long expertId) {
        List<ExpertBranchRequest> foundItems =
                daoRepositories.getExpertBranchRepository().findExpertBranchRequestsByExpert_Id(expertId);
        List<ExpBrBasicResult> resultList = new ArrayList<>();
        foundItems.forEach(item -> {
            ExpBrBasicResult result = expertBranchMapper.expertBranchToExpBrBasicResult(item);
            resultList.add(result);
        });
        return resultList;
    }

    @Override
    public List<ExpBrBasicResult> findAllRequestByBranchId(Long branchId) {
        List<ExpertBranchRequest> foundItems =
                daoRepositories.getExpertBranchRepository().findExpertBranchRequestsByBranch_Id(branchId);
        List<ExpBrBasicResult> resultList = new ArrayList<>();
        foundItems.forEach(item -> {
            ExpBrBasicResult result = expertBranchMapper.expertBranchToExpBrBasicResult(item);
            resultList.add(result);
        });
        return resultList;
    }

    @Override
    public boolean isExistRequestToBranchByThisUserId(ExpBrParam param) {
        return daoRepositories
                .getExpertBranchRepository()
                .existsByExpert_IdAndBranch_Id(param.getUserId(), param.getBranchId());
    }

    @Override
    public void deleteRequest(ExpBrParam param) {
        if (isExistRequestToBranchByThisUserId(param)){
        ExpertBranchRequest requestToDelete =
                daoRepositories
                        .getExpertBranchRepository()
                        .findByExpert_IdAndBranch_Id(param.getUserId(), param.getBranchId());
        daoRepositories.getExpertBranchRepository().delete(requestToDelete);
        }


    }

    @Override
    public ExpBrBasicResult changeRequestStatus(ExpBrActivationParam param) {
        ExpBrParam expBrParam=new ExpBrParam(param.getUserId(), param.getBranchId());
        if (isExistRequestToBranchByThisUserId(expBrParam)) {
            ExpertBranchRequest request =
                    daoRepositories
                            .getExpertBranchRepository()
                            .findByExpert_IdAndBranch_Id(param.getUserId(), param.getBranchId());
            request.setApproved(param.isStatus());
            daoRepositories.getExpertBranchRepository().save(request);
            return expertBranchMapper.expertBranchToExpBrBasicResult(request);

        }
        throw new EntityNotFoundException("request not found");
    }
}
