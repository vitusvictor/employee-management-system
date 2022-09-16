package com.ema.exception;

public class EmailAlreadyConfirmed extends RuntimeException {
    public EmailAlreadyConfirmed(String message) {
        super(message);
    }
}

