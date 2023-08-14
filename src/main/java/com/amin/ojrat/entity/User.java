package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user_table")
public class User extends BaseEntity<Long> {

     private String firstName;

     private String lastName;

     private String email;

     private String password;

     private String nationalCode;

     private String address;

     private String phoneNumber;

     @OneToMany(mappedBy = "sender",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     private List<Message> sentMessages=new ArrayList<>();

     @OneToMany(mappedBy = "receiver",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     private List<Message> receivedMessages=new ArrayList<>();

     @CreationTimestamp
     private LocalTime createdAt;

     public User() {
     }

     public User(Long id, String firstName, String lastName,
                 String email, String password, String nationalCode,
                 String address, String phoneNumber, List<Message> sentMessages,
                 List<Message> receivedMessages, LocalTime createdAt) {
          super(id);
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.password = password;
          this.nationalCode = nationalCode;
          this.address = address;
          this.phoneNumber = phoneNumber;
          this.sentMessages = sentMessages;
          this.receivedMessages = receivedMessages;
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

     public List<Message> getSentMessages() {
          return sentMessages;
     }

     public void setSentMessages(List<Message> sentMessages) {
          this.sentMessages = sentMessages;
     }

     public List<Message> getReceivedMessages() {
          return receivedMessages;
     }

     public void setReceivedMessages(List<Message> receivedMessages) {
          this.receivedMessages = receivedMessages;
     }

     public LocalTime getCreatedAt() {
          return createdAt;
     }

     public void setCreatedAt(LocalTime createdAt) {
          this.createdAt = createdAt;
     }
}
