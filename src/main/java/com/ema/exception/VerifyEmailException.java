package com.ema.exception;

public class VerifyEmailException extends RuntimeException{
    public VerifyEmailException(String message) {
        super(message);
    }
}
