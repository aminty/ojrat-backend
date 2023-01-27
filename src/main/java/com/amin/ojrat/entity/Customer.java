package com.amin.ojrat.entity;

import com.amin.ojrat.enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_table")
public class Customer extends User{

    private Role role ;


    @OneToOne
    private Wallet wallet;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany
    private List<Message> messages;

}
