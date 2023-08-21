package com.amin.ojrat.dto.entity.ExBrReq.response;


import java.time.LocalDate;

public class ExpBrBasicResult {

    Long expertId;

    Long branchId;

    String branchName;

    String expertName;

    boolean requestStatus;

    LocalDate createdAt;

    LocalDate updatedAt;

    public ExpBrBasicResult() {
    }

    public ExpBrBasicResult(Long expertId,
                            Long branchId,
                            String branchName,
                            String expertName,
                            boolean requestStatus,
                            LocalDate createdAt,
                            LocalDate updatedAt) {
        this.expertId = expertId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.expertName = expertName;
        this.requestStatus = requestStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
