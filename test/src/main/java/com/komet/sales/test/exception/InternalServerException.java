package com.komet.sales.test.exception;

public class InternalServerException extends RuntimeException{
    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }
}
