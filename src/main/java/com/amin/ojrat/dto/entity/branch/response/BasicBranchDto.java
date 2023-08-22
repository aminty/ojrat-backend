package com.amin.ojrat.dto.entity.branch.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BasicBranchDto {

    private Long id;

    private String name;

    private String location;

    private String phone;

    private String description;

    public BasicBranchDto() {
    }

    public BasicBranchDto(Long id, String name, String location, String phone, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
