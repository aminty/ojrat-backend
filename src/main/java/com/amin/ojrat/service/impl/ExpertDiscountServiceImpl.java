package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.ExpertDiscount;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.ExpertDiscountService;
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
    public void setDiscountForExpert(Branch branch, Expert expert, double discount) {
        if (!isExistsDiscountForThisExpertInBranch(branch.getId(), expert.getId())) {
            ExpertDiscount expertDiscount = new ExpertDiscount();
            expertDiscount.setExpert(expert);
            expertDiscount.setBranch(branch);
            expertDiscount.setDiscountPercentage(discount);
            daoRepositories.getExpertDiscountRepository().save(expertDiscount);
        }
    }

    @Override
    public boolean isExistsDiscountForThisExpertInBranch(Long branchId, Long expertId) {
        return daoRepositories
                .getExpertDiscountRepository()
                .existsByBranch_IdAndExpert_Id(branchId, expertId);
    }
}
