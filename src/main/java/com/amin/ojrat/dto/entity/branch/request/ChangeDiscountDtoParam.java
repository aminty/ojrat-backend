package com.amin.ojrat.dto.entity.branch.request;

import jakarta.validation.constraints.NotNull;

public class ChangeDiscountDtoParam {


    @NotNull(message = "expert id should not be null")
    Long expertId;

    @NotNull(message = "branch id should not be null")
    Long branchId;

    @NotNull(message = "percent should not be empty")
    Double percent;

    public ChangeDiscountDtoParam() {
    }

    public ChangeDiscountDtoParam(Long expertId, Long branchId, double percent) {
        this.expertId = expertId;
        this.branchId = branchId;
        this.percent = percent;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
