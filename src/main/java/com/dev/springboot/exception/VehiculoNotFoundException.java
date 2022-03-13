package com.dev.springboot.exception;

public class VehiculoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VehiculoNotFoundException() {
        super();
    }

    /**
     * Pasa al constructor el custom error
     * @param customMessage mensaje de error para mostrar
     */
    public VehiculoNotFoundException(String customMessage) {
        super(customMessage);
    }
}