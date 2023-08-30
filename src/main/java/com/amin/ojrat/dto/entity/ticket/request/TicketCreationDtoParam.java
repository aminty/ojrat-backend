package com.amin.ojrat.dto.entity.ticket.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TicketCreationDtoParam {


    @NotNull(message = "subject should not be null")
    private Long subjectId;
    @NotEmpty(message = "description should not be empty")
    private String description;
    @NotNull(message = "expertId should not be null")
    private Long expertId;
    @NotNull(message = "branchId should not be null")
    private Long branchId;
    @NotEmpty(message = "message should not be empty")
    private String message;
    @NotNull(message = "senderId should not be null")
    private Long senderId;

    public TicketCreationDtoParam() {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
