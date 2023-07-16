package com.amin.ojrat.dto.payamak.status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GetSmsStatusResult {

    List<String> results;

    List<Long> resultsAsCode;

    String status;
}
