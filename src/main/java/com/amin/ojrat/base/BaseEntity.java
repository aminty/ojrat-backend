package com.amin.ojrat.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass


public class BaseEntity<T extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private T Id;

    public BaseEntity() {
    }

    public BaseEntity(T id) {
        Id = id;
    }

    public T getId() {
        return Id;
    }

    public void setId(T id) {
        Id = id;
    }
}
