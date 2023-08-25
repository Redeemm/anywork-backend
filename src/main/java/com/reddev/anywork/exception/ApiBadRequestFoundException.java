package com.reddev.anywork.exception;

public class ApiBadRequestFoundException extends RuntimeException {

    public ApiBadRequestFoundException(String message) {
        super(message);
    }

    public ApiBadRequestFoundException(Throwable cause) {
        super(cause);
    }
}
