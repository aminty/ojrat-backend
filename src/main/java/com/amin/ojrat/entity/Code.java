package com.amin.ojrat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Code")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class Code implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String phoneNumber;

    String code;
    public Code(String phoneNumber, String code) {
    }
}
