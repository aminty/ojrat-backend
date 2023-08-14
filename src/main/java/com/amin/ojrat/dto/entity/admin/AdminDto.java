package com.amin.ojrat.dto.entity.admin;

import com.amin.ojrat.dto.entity.branch.BranchDto;
import com.amin.ojrat.dto.entity.user.UserDto;
import com.amin.ojrat.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;


public class AdminDto extends UserDto {

    @NotEmpty(message = "roles should not be blank!")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    private BranchDto branch;

    public AdminDto() {
    }



    public AdminDto(Long id, String firstName, String lastName, String email, String password, String nationalCode, String address, String phoneNumber, List<Role> roles, BranchDto branch) {
        super(id, firstName, lastName, email, password, nationalCode, address, phoneNumber);
        this.roles = roles;
        this.branch = branch;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public BranchDto getBranch() {
        return branch;
    }

    public void setBranch(BranchDto branch) {
        this.branch = branch;
    }
}


