package com.amin.ojrat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service extends BaseEntity<Long>{

    private String description;

    private double wage;

    private String unit;

    private boolean isPresent;
}
