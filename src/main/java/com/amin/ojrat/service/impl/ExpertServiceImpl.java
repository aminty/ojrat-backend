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
        Expert expert = expertMapper.expertCreationDtoToExpert(param);
        Expert saveExpert = daoRepositories.getExpertRepository().save(expert);
        if (saveExpert.getId() == null) {
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

        ExpertBranchRequest expBr = createExpertBranchRequest(userId, branchId);

        expertBranchService.saveJoinRequest(expBr);
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

    @Override
    public Expert findExpert(Long id) {
        return daoRepositories.getExpertRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expert not found"));
    }

    private ExpertBranchRequest createExpertBranchRequest(Long userId, Long branchId) throws Exception {
        Expert foundExpert = findExpert(userId);
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
