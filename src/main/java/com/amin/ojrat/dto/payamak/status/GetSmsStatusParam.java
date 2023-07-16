package com.amin.ojrat.dto.payamak.status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GetSmsStatusParam {

     List<Long> recIds;
}
