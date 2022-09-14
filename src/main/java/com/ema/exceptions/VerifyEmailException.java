package com.ema.exceptions;

public class VerifyEmailException extends RuntimeException{
    public VerifyEmailException(String message) {
        super(message);
    }
}
