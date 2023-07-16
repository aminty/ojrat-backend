package com.amin.ojrat.entity;

import com.amin.ojrat.base.BaseEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Document(collection = "code-verification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class Code implements Serializable {
    @MongoId
    Long id;

    String phoneNumber;

    String code;

}
