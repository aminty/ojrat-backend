package com.amin.ojrat.dto.entity.ExBrReq.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExpBrParam {

    @NotNull(message = "user id should not be null")
    Long userId;

    @NotNull(message = "branch id should not be null")
    Long branchId;

    public ExpBrParam(Long userId, Long branchId) {
        this.userId = userId;
        this.branchId = branchId;
    }

    public ExpBrParam() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
