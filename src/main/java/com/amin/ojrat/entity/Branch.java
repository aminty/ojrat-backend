package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
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

    @Column(unique = true)
    private String uniqueName;


    @OneToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @ManyToMany(mappedBy = "branches")
    private List<Expert> experts ;

    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ExpertBranchRequest> requests;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpertDiscount> expertDiscounts = new ArrayList<>();

    private boolean status;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;




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
                  boolean status, Timestamp createdAt,
                  Timestamp updatedAt,String uniqueName) {
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
        this.uniqueName=uniqueName ;
    }

    public void addToExpertList(Expert expert) {
        this.experts.add(expert);
        expert.getBranches().add(this);
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public List<ExpertDiscount> getExpertDiscounts() {
        return expertDiscounts;
    }

    public void setExpertDiscounts(List<ExpertDiscount> expertDiscounts) {
        this.expertDiscounts = expertDiscounts;
    }


}