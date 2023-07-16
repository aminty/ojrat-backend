package com.amin.ojrat.dto.payamak.send;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SendSmsParam {

    String from;
    String to;
    String text;
}
