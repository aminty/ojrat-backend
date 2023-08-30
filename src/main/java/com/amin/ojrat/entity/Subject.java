package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@Entity
public class Subject extends BaseEntity<Long> {

    private String title;

    @CreationTimestamp
    private Timestamp createdAt;

    public String getTitle() {
        return title;
    }

    public void setSubject(String title) {
        this.title = title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
