package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.entity.branch.response.BasicBranchDto;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.dto.mapper.BranchMapper;
import com.amin.ojrat.dto.mapper.ExpertMapper;
import com.amin.ojrat.entity.*;
import com.amin.ojrat.exception.*;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.ExpertBranchService;
import com.amin.ojrat.service.ExpertService;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {

    private final UserService userService;
    private final BranchService branchService;
    private final ExpertBranchService expertBranchService;
    private final DaoRepositories daoRepositories;
    private final ExpertMapper expertMapper;
    private final BranchMapper branchMapper;

    @Autowired
    public ExpertServiceImpl(UserService userService,
                             BranchService branchService,
                             ExpertBranchService expertBranchService,
                             DaoRepositories daoRepositories,
                             ExpertMapper expertMapper, BranchMapper branchMapper) {
        this.userService = userService;
        this.branchService = branchService;
        this.expertBranchService = expertBranchService;
        this.daoRepositories = daoRepositories;
        this.expertMapper = expertMapper;
        this.branchMapper = branchMapper;
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
        );  
    }

    @Override
    public void makeJoinRequest(ExpBrParam param) throws Exception {
        Long userId = param.getUserId();
        Long branchId = param.getBranchId();
        ExpertBranchRequest expBr = new ExpertBranchRequest();

        Branch foundBranch = branchService.findBranchById(branchId);
        Expert foundExpert = findExpert(userId);
       if (foundExpert.isIsDeleted())
           throw new UserExistsException("it's seems to like the user was deleted.");
       if (!foundExpert.isActive())
           throw new ActivationException("it seems to like user does not active yet.");
        if (branchService.isBranchFullyRegistered(foundBranch)) {
            if (expertBranchService.countOfRequestByExpertId(userId) > 5)
                throw new RequestLimitExceededException("You can't send more than 5 requests");

            if (isExpertExistsInBranch(userId, foundBranch))
                throw new UserExistsException("You already joined this branch");

            if (!foundBranch.isStatus())
                throw new LicenseStatusException("Store is not active yet");


            expBr.setExpert(foundExpert);
            expBr.setBranch(foundBranch);
            expertBranchService.saveJoinRequest(expBr);
        } else {
            throw new NotFullyRegisteredException("Store is not initialized");
        }
    }


    @Override
    public boolean isExpertExistsInBranch(Long userId, Branch foundBranch) {
        return foundBranch.getExperts().stream().anyMatch(expert -> Objects.equals(expert.getId(), userId));
    }

    @Override
    public List<ExpBrBasicResult> getAllJoinRequest(Long expertId) {
        if (daoRepositories.getExpertRepository().existsById(expertId))
            return expertBranchService.findAllRequestByExpertId(expertId);
        else throw new EntityNotFoundException("Expert not found");
    }

    @Override
    public Page<BasicBranchDto> getAllAllowedBranch(Pageable pageable) {
        Page<Branch> allBranchByStatusTrue = branchService.findAllBranchByStatusTrue(pageable);
        return allBranchByStatusTrue.map(branchMapper::branchToBasicBranchDto);
    }


    public Expert findExpert(Long id) {
        return daoRepositories.getExpertRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found"));
    }

}
