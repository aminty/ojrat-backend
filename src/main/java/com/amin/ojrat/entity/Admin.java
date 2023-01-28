package com.amin.ojrat.entity;

import com.amin.ojrat.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "admin_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)

public class Admin extends User{



     Role role ;

    @OneToMany
     List<Message> messages;

}
