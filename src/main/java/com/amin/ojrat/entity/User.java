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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user_table")
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)

public class User extends BaseEntity<Long> {

     String firstName;

     String lastName;

     String email;

     String password;

     String nationalCode;

     String address;

     String phoneNumber;

     @OneToMany(mappedBy = "sender",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     List<Message> sentMessages=new ArrayList<>();

     @OneToMany(mappedBy = "receiver",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     List<Message> receivedMessages=new ArrayList<>();

     @CreationTimestamp
     LocalTime createdAt;
}
