package com.amin.ojrat.entity;

import com.amin.ojrat.enumeration.OrderStatus;
import com.amin.ojrat.enumeration.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity<Long> {
    private String description;


    @ManyToOne
    private ServiceProvider serviceProvider;

    @ManyToOne
    private Customer customer;


    @OneToMany
    private List<Service> services;

    @OneToMany
    private List<Product> products;

    private OrderStatus status;

    private PaymentType paymentType;








}
