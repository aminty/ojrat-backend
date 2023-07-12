package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)
public class Product extends BaseEntity<Long> {

     String productName;

     String description;

     double price;

     double profitPercent;

     double profit;

     double discount;

     boolean isExist;

     @ManyToOne
     Branch branch;






}
