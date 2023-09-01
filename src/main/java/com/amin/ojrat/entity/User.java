package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user_table")
@Where(clause = "is_deleted = true")
public class User extends BaseEntity<Long> {

     private String firstName;

     private String lastName;

     @NaturalId
     @Column(unique = true)
     private String email;

     private String password;

     @Column(unique = true)
     private String nationalCode;

     private String address;

     @Column(unique = true)
     private String phoneNumber;


     @CreationTimestamp
     private Timestamp createdAt;

     private boolean isActive;

     private boolean isDeleted;

     public User() {
     }

     public User(Long id, String firstName, String lastName,
                 String email, String password, String nationalCode,
                 String address, String phoneNumber,
                 Timestamp createdAt
                 ) {
          super(id);
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.password = password;
          this.nationalCode = nationalCode;
          this.address = address;
          this.phoneNumber = phoneNumber;
          this.createdAt = createdAt;
     }

     public String getFirstName() {
          return firstName;
     }

     public void setFirstName(String firstName) {
          this.firstName = firstName;
     }

     public String getLastName() {
          return lastName;
     }

     public void setLastName(String lastName) {
          this.lastName = lastName;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getNationalCode() {
          return nationalCode;
     }

     public void setNationalCode(String nationalCode) {
          this.nationalCode = nationalCode;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getPhoneNumber() {
          return phoneNumber;
     }

     public void setPhoneNumber(String phoneNumber) {
          this.phoneNumber = phoneNumber;
     }

     public Timestamp getCreatedAt() {
          return createdAt;
     }

     public void setCreatedAt(Timestamp createdAt) {
          this.createdAt = createdAt;
     }

     public boolean isActive() {
          return isActive;
     }

     public void setActive(boolean active) {
          isActive = active;
     }

     public boolean isIsDeleted() {
          return isDeleted;
     }

     public void setIsDeleted(boolean idDeleted) {
          this.isDeleted = idDeleted;
     }
}
