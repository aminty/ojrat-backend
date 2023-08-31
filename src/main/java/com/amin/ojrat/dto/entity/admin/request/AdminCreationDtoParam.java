package com.amin.ojrat.dto.entity.admin.request;

import com.amin.ojrat.dto.entity.user.request.UserCreationDtoParam;
import com.amin.ojrat.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class AdminCreationDtoParam extends UserCreationDtoParam {

    @NotEmpty(message = "roles should not be blank!")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public AdminCreationDtoParam() {
    }

    public AdminCreationDtoParam(String firstName, String lastName,
                                 String email, String password,
                                 String nationalCode, String address,
                                 String phoneNumber, List<Role> roles) {

        super(firstName, lastName, email, password, nationalCode, address, phoneNumber);
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
