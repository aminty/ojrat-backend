package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.branch.request.ChangeDiscountDtoParam;
import com.amin.ojrat.entity.ExpertDiscount;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.ExpertDiscountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertDiscountServiceImpl implements ExpertDiscountService {

    private final DaoRepositories daoRepositories;

    @Autowired
    public ExpertDiscountServiceImpl(DaoRepositories daoRepositories) {
        this.daoRepositories = daoRepositories;
    }



    @Override
    public boolean isExistsDiscountForThisExpertInBranch(Long branchId, Long expertId) {
        return daoRepositories
                .getExpertDiscountRepository()
                .existsByBranch_IdAndExpert_Id(branchId, expertId);
    }

    @Override
    public void deleteDiscountByIds(Long expertId, Long branchId) {
        if (daoRepositories.getExpertDiscountRepository().existsByBranch_IdAndExpert_Id(branchId, expertId))
            daoRepositories.getExpertDiscountRepository().deleteByExpert_IdAndBranch_Id(expertId, branchId);

    }

    @Override
    public ExpertDiscount findExpertDiscount(ChangeDiscountDtoParam param) {
        if (daoRepositories
                .getExpertDiscountRepository()
                .existsByBranch_IdAndExpert_Id(param.getBranchId(), param.getExpertId())) {
            return daoRepositories
                    .getExpertDiscountRepository()
                    .findExpertDiscountByBranch_IdAndExpert_Id(param.getBranchId(),
                            param.getExpertId());

        } else throw new EntityNotFoundException("no discount found for expert");
    }


    @Override
    public void updateDiscount(ExpertDiscount expertDiscount) {
        daoRepositories.getExpertDiscountRepository().save(expertDiscount);
    }
}
