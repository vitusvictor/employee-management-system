package com.ema.customExceptions;

public class FailedMailException extends RuntimeException{
     public FailedMailException(String message) {
        super(message);
    }
}
