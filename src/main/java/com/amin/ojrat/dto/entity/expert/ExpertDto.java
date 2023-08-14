package com.amin.ojrat.dto.entity.expert;

import com.amin.ojrat.dto.entity.ExBrReq.ExpertBranchDto;
import com.amin.ojrat.dto.entity.branch.BranchDto;
import com.amin.ojrat.dto.entity.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;



public class ExpertDto extends UserDto {

    private List<BranchDto> branches;


    private List<ExpertBranchDto> branchRequests;


    public ExpertDto() {
    }

    public ExpertDto(Long id, String firstName, String lastName,
                     String email, String password, String nationalCode,
                     String address, String phoneNumber,
                     List<BranchDto> branches, List<ExpertBranchDto> branchRequests) {
        super(id, firstName, lastName, email, password, nationalCode, address, phoneNumber);
        this.branches = branches;
        this.branchRequests = branchRequests;
    }

    public List<BranchDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDto> branches) {
        this.branches = branches;
    }

    public List<ExpertBranchDto> getBranchRequests() {
        return branchRequests;
    }

    public void setBranchRequests(List<ExpertBranchDto> branchRequests) {
        this.branchRequests = branchRequests;
    }
}
