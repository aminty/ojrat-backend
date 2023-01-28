package com.amin.ojrat.entity;

import com.amin.ojrat.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToMany(mappedBy = "expert")
    List<Order> orders;



    @OneToMany
    List<Message> messages;
}
