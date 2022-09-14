package com.ema.exception;

public class SuperAdminAlreadyExists extends RuntimeException {
    public SuperAdminAlreadyExists(String message) {
        super(message);
    }
}
