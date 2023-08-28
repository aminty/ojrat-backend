package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.mapper.ExpertBranchMapper;
import com.amin.ojrat.entity.ExpertBranchRequest;
import com.amin.ojrat.entity.ExpertDiscount;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.ExpertBranchRequestService;
import com.amin.ojrat.service.ExpertDiscountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class ExpertBranchRequestServiceImpl implements ExpertBranchRequestService {

    private final DaoRepositories daoRepositories;
    private final ExpertBranchMapper expertBranchMapper;
    private final ExpertDiscountService expertDiscountService;
    private final BranchService branchService;


    @Autowired
    public ExpertBranchRequestServiceImpl(DaoRepositories daoRepositories,
                                          ExpertBranchMapper expertBranchMapper,
                                          ExpertDiscountService expertDiscountService,
                                          @Lazy BranchService branchService) {
        this.daoRepositories = daoRepositories;
        this.expertBranchMapper = expertBranchMapper;
        this.expertDiscountService = expertDiscountService;

        this.branchService = branchService;
    }


    @Override
    @Transactional
    public void saveJoinRequest(ExpertBranchRequest expBr) {
        daoRepositories.getExpertBranchRequestRepository().save(expBr);

    }

    @Override
    public Long countOfRequestByExpertId(Long expertId) {
        return daoRepositories.getExpertBranchRequestRepository().countByExpert_Id(expertId);
    }

    @Override
    public List<ExpBrBasicDtoResult> findAllRequestByExpertId(Long expertId) {
        List<ExpertBranchRequest> foundItems =
                daoRepositories.getExpertBranchRequestRepository().findExpertBranchRequestsByExpert_Id(expertId);
        List<ExpBrBasicDtoResult> resultList = new ArrayList<>();
        foundItems.forEach(item -> {
            ExpBrBasicDtoResult result = expertBranchMapper.expertBranchToExpBrBasicResult(item);
            resultList.add(result);
        });
        return resultList;
    }

    @Override
    public List<ExpBrBasicDtoResult> findAllRequestByBranchId(Long branchId) {
        List<ExpertBranchRequest> foundItems =
                daoRepositories.getExpertBranchRequestRepository().findExpertBranchRequestsByBranch_Id(branchId);
        List<ExpBrBasicDtoResult> resultList = new ArrayList<>();
        foundItems.forEach(item -> {
            ExpBrBasicDtoResult result = expertBranchMapper.expertBranchToExpBrBasicResult(item);
            resultList.add(result);
        });
        return resultList;
    }

    @Override
    public boolean isExistRequest(Long requestId) {
        return daoRepositories
                .getExpertBranchRequestRepository()
                .existsById(requestId);
    }

    @Override
    public boolean isExistRequestByExpertAndBranchIds(ExpBrDtoParam param) {
        return daoRepositories
                .getExpertBranchRequestRepository()
                .existsByExpert_IdAndBranch_Id(param.getExpertId(), param.getBranchId());
    }

    @Override
    public void deleteRequest(Long requestId) throws DeletionException {
        if (isExistRequest(requestId)) {
            Optional<ExpertBranchRequest> foundRequest = daoRepositories
                    .getExpertBranchRequestRepository()
                    .findById(requestId);
            if (foundRequest.isPresent()) {
                ExpertBranchRequest request = foundRequest.get();
                if (!request.isApproved())
                    daoRepositories.getExpertBranchRequestRepository().delete(request);
                else throw new DeletionException("you can delete unaccepted request");
            }
        }


    }

    @Override
    public void deleteRequestByIdsInsideExpertRelationDeletionInBranchService(Long expertId, Long branchID) {
        boolean isExists = daoRepositories
                .getExpertBranchRequestRepository()
                .existsByExpert_IdAndBranch_Id(expertId, branchID);
        if (isExists)
            daoRepositories
                    .getExpertBranchRequestRepository()
                    .deleteByExpert_IdAndBranch_Id(expertId, branchID);


    }

    @Override
    @Transactional
    public ExpBrBasicDtoResult changeRequestStatus(ExpBrActivationDtoParam param) throws ChangeStatusException {
        Optional<ExpertBranchRequest> foundRequest
                = daoRepositories.getExpertBranchRequestRepository().findById(param.getId());
        if (foundRequest.isPresent()) {
            ExpertBranchRequest request = foundRequest.get();
            if (request.isApproved() && !param.isStatus())
                throw new ChangeStatusException("you could not reject the accepted request!");
            if (!request.isApproved() && param.isStatus()) {
                request.setApproved(param.isStatus());
                setDiscount(request);
                branchService.save(request.getBranch());
            }
            return expertBranchMapper.expertBranchToExpBrBasicResult(request);
        }
        throw new EntityNotFoundException("request not found");
    }

    private void setDiscount(ExpertBranchRequest request) {
        boolean result = expertDiscountService.isExistsDiscountForThisExpertInBranch(
                request.getBranch().getId()
                , request.getExpert().getId());
        if (!result) {
            ExpertDiscount discount = new ExpertDiscount();
            discount.setExpert(request.getExpert());
            discount.setBranch(request.getBranch());
            discount.setDiscountPercentage(0);
            request.getBranch().addExpertDiscount(discount);
            request.getBranch().addToExpertList(request.getExpert());
        }
    }

}
