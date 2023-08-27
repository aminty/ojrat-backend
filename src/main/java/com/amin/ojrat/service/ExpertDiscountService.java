package com.amin.ojrat.service;

import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;

public interface ExpertDiscountService {

    boolean isExistsDiscountForThisExpertInBranch(Long branchId, Long expertId);

    void deleteDiscountByIds(Long expertId,Long branchId);


}
