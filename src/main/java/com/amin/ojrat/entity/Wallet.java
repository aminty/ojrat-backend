package com.amin.ojrat.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wallet_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseEntity<Long> {

    private String cardNumber;

    private String cvv2;

    private double credit;

    private String pin;
}
