package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ExpertDiscount extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Expert expert;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    private double discountPercentage;

    public ExpertDiscount() {
    }

    public ExpertDiscount(Long id, Expert expert, Branch branch, double discountPercentage) {
        super(id);
        this.expert = expert;
        this.branch=branch;
        this.discountPercentage = discountPercentage;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }


    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}