package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "wallet_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false,level = AccessLevel.PRIVATE)

public class Wallet extends BaseEntity<Long> {

     String cardNumber;

     String cvv2;

     double credit;

     String pin;
}
