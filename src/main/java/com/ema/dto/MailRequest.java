package com.ema.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MailRequest {
    private String to;
    private String name;
    private String subject;
    private String body;
}
