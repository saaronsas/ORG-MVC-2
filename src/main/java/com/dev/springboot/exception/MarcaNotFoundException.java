package com.dev.springboot.exception;

public class MarcaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MarcaNotFoundException() {
        super();
    }

    public MarcaNotFoundException(String customMessage) {
        super(customMessage);
    }
}