package com.amin.ojrat.entity;

import com.amin.ojrat.enums.Role;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "admin_table")

public class Admin extends User {


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "admin_role", joinColumns = @JoinColumn(name = "admin_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @OneToOne(mappedBy = "admin",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
   private Branch branch;

    public Admin() {
    }

    public Admin(Long id, String firstName, String lastName,
                 String email, String password, String nationalCode,
                 String address, String phoneNumber
               , Timestamp createdAt, List<Role> roles,
                 Branch branch) {
        super(id, firstName, lastName, email, password, nationalCode,
                address, phoneNumber, createdAt);
        this.roles = roles;
        this.branch = branch;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
