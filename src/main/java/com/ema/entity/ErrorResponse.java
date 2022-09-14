package com.ema.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String debugMessage;
    private HttpStatus httpStatus;
    private LocalDateTime date = LocalDateTime.now();
}
