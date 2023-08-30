package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.entity.branch.response.BasicBranchDtoResult;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDtoParam;
import com.amin.ojrat.dto.mapper.BranchMapper;
import com.amin.ojrat.dto.mapper.ExpertMapper;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.ExpertBranchRequest;
import com.amin.ojrat.exception.*;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.ExpertBranchRequestService;
import com.amin.ojrat.service.ExpertService;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ExpertServiceImpl implements ExpertService {

    private final DaoRepositories daoRepositories;
    private final BranchMapper branchMapper;
    private final ExpertMapper expertMapper;
    private final BranchService branchService;
    private final UserService userService;
    private final ExpertBranchRequestService expertBranchService;

    @Autowired
    public ExpertServiceImpl(UserService userService,
                             @Lazy BranchService branchService,
                             ExpertBranchRequestService expertBranchService,
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
    public void saveExpert(ExpertCreationDtoParam param) throws DuringSaveException {
        Expert expert = expertMapper.expertCreationDtoToExpert(param);
        Expert saveExpert = daoRepositories.getExpertRepository().save(expert);
        if (saveExpert.getId() == null) {
            throw new DuringSaveException("new record doesn't save");
        }
    }

    @Override
    public void updateExpert(Expert expert) {
        daoRepositories.getExpertRepository().save(expert);
    }

    @Override
    public boolean isExistsExpertByValue(ExpertCreationDtoParam param) {
        return userService.isUserExistsByValue(
                param.getNationalCode(), param.getEmail(), param.getPhoneNumber()
        );
    }

    @Override
    public void makeJoinRequest(ExpBrDtoParam param) throws Exception {
        Long userId = param.getExpertId();
        Long branchId = param.getBranchId();
        checkIfRequestIsExistsThenThrow(param);
        ExpertBranchRequest expBr = createExpertBranchRequest(userId, branchId);
        expertBranchService.saveJoinRequest(expBr);
    }

    private void checkIfRequestIsExistsThenThrow(ExpBrDtoParam param)
            throws RequestLimitExceededException {
        boolean checkExists =
                expertBranchService.isExistRequestByExpertAndBranchIds(param);
        if (checkExists)
            throw new RequestLimitExceededException("you already have request for this branch");

    }


    @Override
    public boolean isExpertExistsInBranch(Long userId, Branch foundBranch) {
        return foundBranch.getExperts().stream().anyMatch(expert -> Objects.equals(expert.getId(), userId));
    }

    @Override
    public List<ExpBrBasicDtoResult> getAllJoinRequest(Long expertId) {
        if (daoRepositories.getExpertRepository().existsById(expertId))
            return expertBranchService.findAllRequestByExpertId(expertId);
        else throw new EntityNotFoundException("Expert not found");
    }

    @Override
    public Page<BasicBranchDtoResult> getAllAllowedBranch(Pageable pageable) {
        Page<Branch> allBranchByStatusTrue = branchService.findAllBranchByStatusTrue(pageable);
        return allBranchByStatusTrue.map(branchMapper::branchToBasicBranchDto);
    }

    @Override
    public Expert findExpertById(Long id) {
        return daoRepositories.getExpertRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found"));
    }

    @Override
    public boolean isExistsById(Long expertId) {
        return daoRepositories.getExpertRepository().existsById(expertId);
    }

    @Override
    public Page<BasicBranchDtoResult> findAvailableBranch(Long expertId, Pageable pageable) {
        Page<Branch> allBranch = daoRepositories
                .getExpertRepository()
                .findAvailableBranchForExpertById(expertId, pageable);
        return allBranch.map(branchMapper::branchToBasicBranchDto);
    }

    private ExpertBranchRequest createExpertBranchRequest(Long userId, Long branchId) throws Exception {
        Expert foundExpert = findExpertById(userId);
        Branch foundBranch = branchService.findBranchById(branchId);

        validateExpertStatus(foundExpert);
        validateBranchStatus(foundBranch);
        validateJoinRequestLimit(userId);
        validateExpertBranchRelation(userId, foundBranch);

        ExpertBranchRequest expBr = new ExpertBranchRequest();
        expBr.setExpert(foundExpert);
        expBr.setBranch(foundBranch);

        return expBr;
    }

    private void validateExpertStatus(Expert expert) throws Exception {
        if (expert.isIsDeleted()) {
            throw new UserExistsException("It seems like the user was deleted.");
        }
        if (!expert.isActive()) {
            throw new ActivationException("It seems like the user is not active yet.");
        }
    }

    private void validateBranchStatus(Branch branch) throws Exception {
        if (!branch.isStatus()) {
            throw new LicenseStatusException("The store is not active yet.");
        }
    }

    private void validateJoinRequestLimit(Long userId) throws Exception {
        if (expertBranchService.countOfRequestByExpertId(userId) > 5) {
            throw new RequestLimitExceededException("You can't send more than 5 requests.");
        }
    }

    private void validateExpertBranchRelation(Long userId, Branch branch) throws Exception {
        if (isExpertExistsInBranch(userId, branch)) {
            throw new UserExistsException("You have already joined this branch.");
        }
        if (!branchService.isBranchFullyRegistered(branch)) {
            throw new NotFullyRegisteredException("The store is not fully initialized.");
        }
    }


}
