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
@Table(name = "service_provider_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProvider extends User {


    private Role role ;


    @OneToMany(mappedBy = "serviceProvider")
    private List<Order> orders;

    @OneToOne
    private Wallet wallet;
}
