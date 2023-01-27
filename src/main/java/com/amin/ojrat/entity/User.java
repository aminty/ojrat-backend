package com.amin.ojrat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user_table")
public class User extends BaseEntity<Long> {

    private  String firstName;

    private String lastName;

    private String email;

    private String password;

    private String nationalCode;

    private String address;


}
