package com.amin.ojrat.dto.entity.ExBrReq.request;

import jakarta.validation.constraints.NotNull;

public class ExpBrDtoParam {

    @NotNull(message = "expert id should not be null")
    Long expertId;

    @NotNull(message = "branch id should not be null")
    Long branchId;

    public ExpBrDtoParam() {
    }

    public ExpBrDtoParam(Long expertId, Long branchId) {
        this.expertId = expertId;
        this.branchId = branchId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
