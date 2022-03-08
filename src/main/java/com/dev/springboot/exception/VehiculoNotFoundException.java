package com.dev.springboot.exception;

public class VehiculoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VehiculoNotFoundException() {
        super();
    }

    public VehiculoNotFoundException(String customMessage) {
        super(customMessage);
    }
}