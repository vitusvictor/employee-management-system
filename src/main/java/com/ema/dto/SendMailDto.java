package com.ema.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMailDto {
    private String receiverEmail;
    private String subject;
    private String senderName;
    private String receiverName;
    private String body;
    private String salutation;
    private String signOff;
    private String link;
}
