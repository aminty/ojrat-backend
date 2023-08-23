package com.amin.ojrat.dto.entity.ExBrReq.request;

import jakarta.validation.constraints.NotNull;

public class ExpBrActivationParam {
    @NotNull(message = "user id should not be null")
    Long userId;

    @NotNull(message = "branch id should not be null")
    Long branchId;


    boolean status;

    public ExpBrActivationParam() {
    }

    public ExpBrActivationParam(Long userId, Long branchId, boolean status) {
        this.userId = userId;
        this.branchId = branchId;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
