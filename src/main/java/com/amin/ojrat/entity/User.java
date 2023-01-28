package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user_table")
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)

public class User extends BaseEntity<Long> {

      String firstName;

     String lastName;

     String email;

     String password;

     String nationalCode;

     String address;


}
