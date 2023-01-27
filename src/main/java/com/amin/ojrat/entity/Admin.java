package com.amin.ojrat.entity;

import com.amin.ojrat.enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User{



    private Role role ;

}
