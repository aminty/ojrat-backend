package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "service_table")

public class Service extends BaseEntity<Long> {

     private String description;

     private double wage;

     private String unit;

     private boolean isPresent;


     public Service() {
     }

     public Service(Long id, String description, double wage, String unit, boolean isPresent) {
          super(id);
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
