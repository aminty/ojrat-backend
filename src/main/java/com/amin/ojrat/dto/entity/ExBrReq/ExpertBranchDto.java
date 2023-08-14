package com.amin.ojrat.dto.entity.ExBrReq;


import com.amin.ojrat.dto.entity.branch.BranchDto;
import com.amin.ojrat.dto.entity.expert.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ExpertBranchDto {


    private ExpertDto expert;


    private BranchDto branch;


    private boolean approved;


    public ExpertBranchDto() {
    }

    public ExpertBranchDto(ExpertDto expert, BranchDto branch, boolean approved) {
        this.expert = expert;
        this.branch = branch;
        this.approved = approved;
    }

    public ExpertDto getExpert() {
        return expert;
    }

    public void setExpert(ExpertDto expert) {
        this.expert = expert;
    }

    public BranchDto getBranch() {
        return branch;
    }

    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
