package com.amin.ojrat.dto.entity.ExBrReq.response;


import java.sql.Timestamp;
import java.time.LocalDate;

public class ExpBrBasicResult {
    Long id;

    Long expertId;

    Long branchId;

    String branchName;

    String expertName;

    String expertLastName;



    boolean requestStatus;

    Timestamp createdAt;

    Timestamp updatedAt;

    public ExpBrBasicResult() {
    }

    public ExpBrBasicResult(Long id,
                            Long expertId,
                            Long branchId,
                            String branchName,
                            String expertName,
                            boolean requestStatus,
                            Timestamp createdAt,
                            Timestamp updatedAt,
                            String expertLastName) {
        this.expertId = expertId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.expertName = expertName;
        this.requestStatus = requestStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expertLastName=expertLastName;
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getExpertLastName() {
        return expertLastName;
    }

    public void setExpertLastName(String expertLastName) {
        this.expertLastName = expertLastName;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }
}
