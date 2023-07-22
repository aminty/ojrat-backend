package com.amin.ojrat.dto.entity.admin;

import com.amin.ojrat.dto.entity.user.UserParam;
import com.amin.ojrat.enums.Role;
//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminParam extends UserParam {

    //@Schema(defaultValue = "roles")
    @NotEmpty(message = "roles should not be blank!")
    @Enumerated(EnumType.STRING)
    List<Role> roles;


}
