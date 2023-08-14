package com.amin.ojrat.dto.entity.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ServiceDto {

    private String description;

    private double wage;

    private String unit;

    private boolean isPresent;


    public ServiceDto() {
    }

    public ServiceDto(String description, double wage, String unit, boolean isPresent) {
        this.description = description;
        this.wage = wage;
        this.unit = unit;
        this.isPresent = isPresent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
