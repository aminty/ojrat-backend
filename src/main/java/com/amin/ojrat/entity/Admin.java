package com.amin.ojrat.entity;

import com.amin.ojrat.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "admin_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)

public class Admin extends User {


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "admin_role", joinColumns = @JoinColumn(name = "admin_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    List<Role> roles;

    @OneToOne(mappedBy = "admin",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    Branch branch;

}
