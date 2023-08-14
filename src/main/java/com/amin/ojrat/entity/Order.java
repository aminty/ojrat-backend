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

public class Order extends BaseEntity<Long> {
    private String description;

    @ManyToOne
    private Expert expert;

    @OneToMany
    private List<Service> services;

    @OneToMany
    private List<Product> products;

    private OrderStatus status;

   private PaymentType paymentType;

    public Order() {
    }

    public Order(Long id, String description, Expert expert,
                 List<Service> services, List<Product> products,
                 OrderStatus status, PaymentType paymentType) {
        super(id);
        this.description = description;
        this.expert = expert;
        this.services = services;
        this.products = products;
        this.status = status;
        this.paymentType = paymentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
