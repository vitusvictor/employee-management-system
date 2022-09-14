package com.ema.exception;

public class DepartmentAlreadyExists extends RuntimeException {
    public DepartmentAlreadyExists(String message) {
        super(message);
    }
}

