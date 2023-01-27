package com.amin.ojrat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;

@Entity
@Table(name = "message_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Message extends BaseEntity<Long> {

    private String text;

    private boolean isRead;


    @CreationTimestamp
    private LocalTime localTime;


}
