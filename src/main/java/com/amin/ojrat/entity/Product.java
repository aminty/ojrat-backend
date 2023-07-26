package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;

@Entity
@Table(name = "product_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)
public class Product extends BaseEntity<Long> {

     String brandName;

     String productName;

     String description;

     double price;

     double discount;

     boolean isExist;

     @CreationTimestamp
     LocalTime createdAt;

     @CreationTimestamp
     LocalTime updatedAt;

     @ManyToOne
     Branch branch;






}
