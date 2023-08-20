package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.dto.mapper.ExpertMapper;
import com.amin.ojrat.entity.*;
import com.amin.ojrat.exception.DuringSaveException;
import com.amin.ojrat.exception.NotFullyRegisteredException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.ExpertService;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {

    private final UserService userService;
    private final BranchService branchService;
    private final DaoRepositories daoRepositories;
    private final ExpertMapper expertMapper;

    @Autowired
    public ExpertServiceImpl(UserService userService, BranchService branchService, DaoRepositories daoRepositories, ExpertMapper expertMapper) {
        this.userService = userService;
        this.branchService = branchService;
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
    public Expert findExpertById(Long expertId) {
        return null;
    }

    @Override
    public List<Product> getAllProductFromBranch(Long branchId) {
        return null;
    }

    @Override
    public boolean isExistsExpertByValue(ExpertCreationDto param) {
        return userService.isUserExistsByValue(
                param.getNationalCode(), param.getEmail(), param.getPhoneNumber()
        );    }

    @Override
    public void makeJoinRequest(ExpBrParam param) throws NotFullyRegisteredException {
        ExpertBranchRequest expBr=new ExpertBranchRequest();
        Branch foundBranch = branchService.findBranchById(param.getBranchId());
        boolean isFullyRegistered = branchService.isBranchFullyRegistered(foundBranch);
        if (isFullyRegistered){
            Expert expert=new Expert();
            expert.setId(param.getUserId());
            expBr.setExpert(expert);
            Branch branch=new Branch();
            branch.setId(param.getBranchId());
            expBr.setBranch(branch);
            daoRepositories.getExpertBranchRepository().save(expBr );
        }else {
            throw new NotFullyRegisteredException("store does not initialized. ");
        }
    }


    public Expert findExpert(Long id) {
        Optional<Expert> foundExpert =
                daoRepositories.getExpertRepository().findById(id);
        if (foundExpert.isPresent()){
           return foundExpert.get();
        }else throw new EntityNotFoundException("expert does not exists.");
    }
}
