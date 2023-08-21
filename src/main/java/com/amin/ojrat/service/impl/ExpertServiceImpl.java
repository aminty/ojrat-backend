package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.dto.mapper.ExpertMapper;
import com.amin.ojrat.entity.*;
import com.amin.ojrat.exception.DuringSaveException;
import com.amin.ojrat.exception.NotFullyRegisteredException;
import com.amin.ojrat.exception.RequestLimitExceededException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.ExpertBranchService;
import com.amin.ojrat.service.ExpertService;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {

    private final UserService userService;
    private final BranchService branchService;
    private final ExpertBranchService expertBranchService;
    private final DaoRepositories daoRepositories;
    private final ExpertMapper expertMapper;

    @Autowired
    public ExpertServiceImpl(UserService userService,
                             BranchService branchService,
                             ExpertBranchService expertBranchService,
                             DaoRepositories daoRepositories,
                             ExpertMapper expertMapper) {
        this.userService = userService;
        this.branchService = branchService;
        this.expertBranchService = expertBranchService;
        this.daoRepositories = daoRepositories;
        this.expertMapper = expertMapper;
    }

    @Override
    @Transactional
    public void saveExpert(ExpertCreationDto param) throws DuringSaveException {
        Expert expert=expertMapper.expertCreationDtoToExpert(param);
        Expert saveExpert = daoRepositories.getExpertRepository().save(expert);
        if (saveExpert.getId()==null){
            throw new DuringSaveException("new record doesn't save");
        }
    }


    @Override
    public boolean isExistsExpertByValue(ExpertCreationDto param) {
        return userService.isUserExistsByValue(
                param.getNationalCode(), param.getEmail(), param.getPhoneNumber()
        );    }

    @Override
    public void makeJoinRequest(ExpBrParam param) throws NotFullyRegisteredException, RequestLimitExceededException {
        ExpertBranchRequest expBr=new ExpertBranchRequest();
        Branch foundBranch = branchService.findBranchById(param.getBranchId());
        boolean isFullyRegistered = branchService.isBranchFullyRegistered(foundBranch);
        Long countOfRequest = expertBranchService.countOfRequestByExpertId(param.getUserId());
        if (countOfRequest>5)
            throw new RequestLimitExceededException("you can't sent more than 5 requests");
        if (isFullyRegistered){
            Expert expert=new Expert();
            expert.setId(param.getUserId());
            expBr.setExpert(expert);
            Branch branch=new Branch();
            branch.setId(param.getBranchId());
            expBr.setBranch(branch);
            expertBranchService.saveJoinRequest(expBr );
        }else {
            throw new NotFullyRegisteredException("store does not initialized. ");
        }
    }

    @Override
    public List<ExpBrBasicResult> getAllJoinRequest(Long expertId) {
        if (daoRepositories.getExpertRepository().existsById(expertId))
            return expertBranchService.findAllRequestByExpertId(expertId);
        else throw new EntityNotFoundException("Expert not found");
    }


    public Expert findExpert(Long id) {
        return daoRepositories.getExpertRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found"));
    }

}
