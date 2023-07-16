package com.amin.ojrat.dto.payamak.validation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class ValidationParam {

    String phoneNumber;
    String code;

}
