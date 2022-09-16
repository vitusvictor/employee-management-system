package com.ema.exception;

public class PasswordsDoNotMatch extends RuntimeException {
    public PasswordsDoNotMatch(String message) {
        super(message);
    }
}

