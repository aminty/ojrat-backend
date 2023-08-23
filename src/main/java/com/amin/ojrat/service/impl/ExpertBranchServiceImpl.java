package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.mapper.ExpertBranchMapper;
import com.amin.ojrat.entity.ExpertBranchRequest;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.ExpertBranchService;
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
}
