package com.work.app.exception;

public class IntegrityViolationException extends RuntimeException {

    public IntegrityViolationException(String message) {
        super(message);
    }
}
