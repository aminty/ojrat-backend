package com.amin.ojrat.dto.entity.ticket.response;

import com.amin.ojrat.dto.entity.base.Context;
import com.amin.ojrat.enums.TicketStatus;

import java.sql.Timestamp;

public class SimpleTicketDtoResult extends Context {

    private Long id;

    private String subject;

    private String description;

    private TicketStatus status;

    private String branchName;

    private Timestamp createdAt;

    private boolean existsNewMessageForExpert;

    private boolean  existsNewMessageForBranch;

    public SimpleTicketDtoResult() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExistsNewMessageForExpert() {
        return existsNewMessageForExpert;
    }

    public void setExistsNewMessageForExpert(boolean existsNewMessageForExpert) {
        this.existsNewMessageForExpert = existsNewMessageForExpert;
    }

    public boolean isExistsNewMessageForBranch() {
        return existsNewMessageForBranch;
    }

    public void setExistsNewMessageForBranch(boolean existsNewMessageForBranch) {
        this.existsNewMessageForBranch = existsNewMessageForBranch;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
