package com.ema.exceptions;

import com.ema.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FailedMailException.class)
    public ResponseEntity<ErrorResponse> handlerForFailedMailException(final FailedMailException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDebugMessage("Mail not sent");
        errorResponse.setHttpStatus(HttpStatus.FAILED_DEPENDENCY);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
