package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.branch.request.ChangeDiscountDtoParam;
import com.amin.ojrat.entity.ExpertDiscount;

public interface ExpertDiscountService {

    boolean isExistsDiscountForThisExpertInBranch(Long branchId, Long expertId);

    void deleteDiscountByIds(Long expertId,Long branchId);

    ExpertDiscount findExpertDiscount(ChangeDiscountDtoParam param);

    void updateDiscount(ExpertDiscount expertDiscount);

}
