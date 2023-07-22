package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.util.List;

@Table(name = "branch_table")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class Branch extends BaseEntity<Long> {

    String name;

    String location;

    String phone;

    String description;

    @OneToOne
    Admin admin;

    @ManyToMany(mappedBy = "branches")
    List<Expert> experts ;

    @OneToMany(mappedBy = "branch")
    List<Product> products;

    boolean status;

    @CreationTimestamp
    LocalTime createdAt;


}