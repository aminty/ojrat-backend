package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(name = "product_table")
public class Product extends BaseEntity<Long> {

     private String brandName;

     private String productName;

     private String description;

     private double price;

    private double discount;

     private boolean isExist;

     @CreationTimestamp
    private Timestamp createdAt;

     @UpdateTimestamp
     private Timestamp updatedAt;

     @ManyToOne(fetch = FetchType.LAZY)
     private Branch branch;

    @Lob
    private byte[] image;


     public Product() {
     }

     public Product(Long id, String brandName, String productName,
                    String description, double price, double discount,
                    boolean isExist, Timestamp createdAt, Timestamp updatedAt, Branch branch) {
          super(id);
          this.brandName = brandName;
          this.productName = productName;
          this.description = description;
          this.price = price;
          this.discount = discount;
          this.isExist = isExist;
          this.createdAt = createdAt;
          this.updatedAt = updatedAt;
          this.branch = branch;
     }

     public String getBrandName() {
          return brandName;
     }

     public void setBrandName(String brandName) {
          this.brandName = brandName;
     }

     public String getProductName() {
          return productName;
     }

     public void setProductName(String productName) {
          this.productName = productName;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public double getPrice() {
          return price;
     }

     public void setPrice(double price) {
          this.price = price;
     }

     public double getDiscount() {
          return discount;
     }

     public void setDiscount(double discount) {
          this.discount = discount;
     }

     public boolean isExist() {
          return isExist;
     }

     public void setExist(boolean exist) {
          isExist = exist;
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

     public Branch getBranch() {
          return branch;
     }

     public void setBranch(Branch branch) {
          this.branch = branch;
     }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
