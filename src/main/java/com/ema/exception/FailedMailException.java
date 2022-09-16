package com.ema.exception;

public class FailedMailException extends RuntimeException{
     public FailedMailException(String message) {
        super(message);
    }
}
