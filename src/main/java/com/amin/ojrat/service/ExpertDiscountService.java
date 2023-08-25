package com.amin.ojrat.service;

import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;

public interface ExpertDiscountService {
    void setDiscountForExpert(Branch branch, Expert expert, double discount);

    boolean isExistsDiscountForThisExpertInBranch(Long id, Long id1);
}
