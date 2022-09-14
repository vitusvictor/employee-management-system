package com.ema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendMailDto {
    private String receiverEmail;
    private String subject;
    private String senderName;
    private String receiverName;
    private String body;
    private String salutation;
    private String signoff;
    private String link;
}
