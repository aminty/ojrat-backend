package com.amin.ojrat.dto.payamak.send;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class SendSmsResult {

    String reqId;

    String status;
}
