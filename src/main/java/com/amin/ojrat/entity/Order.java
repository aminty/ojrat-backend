package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import com.amin.ojrat.enums.OrderStatus;
import com.amin.ojrat.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)

public class Order extends BaseEntity<Long> {
    String description;

    @ManyToOne
    ServiceProvider serviceProvider;

    @ManyToOne
    Customer customer;


    @OneToMany
    List<Service> services;

    @OneToMany
    List<Product> products;

    OrderStatus status;

    PaymentType paymentType;


}
