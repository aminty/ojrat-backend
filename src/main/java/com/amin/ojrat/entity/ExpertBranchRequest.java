package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity

public class ExpertBranchRequest extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    private boolean approved;

    public ExpertBranchRequest() {
    }

    public ExpertBranchRequest(Long id, Expert expert, Branch branch, boolean approved) {
        super(id);
        this.expert = expert;
        this.branch = branch;
        this.approved = approved;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
