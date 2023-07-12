package com.amin.ojrat.entity;

import com.amin.ojrat.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "expert_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)

public class Expert extends User {

    Role role;


    @ManyToMany
    @JoinTable(
            name = "expert_branch",
            joinColumns = @JoinColumn(name = "expert_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    List<Branch> branches;



    @OneToMany(mappedBy = "expert")
    List<Order> orders;




}
