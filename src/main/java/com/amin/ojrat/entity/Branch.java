package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "branch_table")
@Entity
public class Branch extends BaseEntity<Long> {

    private String name;

    private String location;

    private String phone;

    private String description;

    @OneToOne
    private Admin admin;

    @ManyToMany(mappedBy = "branches")
    private List<Expert> experts ;

    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "branch",orphanRemoval = true)
    private List<ExpertBranchRequest> requests;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpertDiscount> expertDiscounts = new ArrayList<>();

    private boolean status;

    @CreationTimestamp
    private LocalTime createdAt;

    @CreationTimestamp
    private LocalTime updatedAt;




    public void addExpertDiscount(ExpertDiscount discount) {
        expertDiscounts.add(discount);
        discount.setBranch(this);
    }

    public void removeExpertDiscount(ExpertDiscount discount) {
        expertDiscounts.remove(discount);
        discount.setBranch(null);
    }

    public Branch() {
    }

    public Branch(Long id, String name, String location,
                  String phone, String description,
                  Admin admin, List<Expert> experts,
                  List<Product> products, List<ExpertBranchRequest> requests,
                  boolean status, LocalTime createdAt, LocalTime updatedAt) {
        super(id);
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.admin = admin;
        this.experts = experts;
        this.products = products;
        this.requests = requests;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ExpertBranchRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<ExpertBranchRequest> requests) {
        this.requests = requests;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}