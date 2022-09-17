package com.ema.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private HttpStatus httpStatus;
    private String message;
    private String debugMessage;
    private LocalDateTime time = LocalDateTime.now();

    public ErrorResponseDto(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
