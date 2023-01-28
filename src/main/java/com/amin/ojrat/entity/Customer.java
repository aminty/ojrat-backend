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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_table")
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)

public class Customer extends User{

     Role role ;


    @OneToMany(mappedBy = "customer")
     List<Order> orders;

    @OneToMany
     List<Message> messages;

}
