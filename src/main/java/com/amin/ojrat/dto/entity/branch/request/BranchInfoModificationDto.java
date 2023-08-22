package com.amin.ojrat.dto.entity.branch.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BranchInfoModificationDto {


    @NotNull(message = "branch id should not be null")
    private Long id;


    @NotEmpty(message = "name should not be empty.")
    private String name;


    @NotEmpty(message = "address should not be empty.")
    private String location;


    @NotEmpty(message = "phone should not be empty.")
    private String phone;


    @NotEmpty(message = "description should not be empty.")
    private String description;


    public BranchInfoModificationDto() {

    }

    public BranchInfoModificationDto(Long id, String name, String location, String phone, String description) {
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
