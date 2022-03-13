package com.dev.springboot.exception;

public class MarcaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MarcaNotFoundException() {
        super();
    }

    /**
     * Pasa al constructor el custom error
     * @param customMessage mensaje de error para mostrar
     */
    public MarcaNotFoundException(String customMessage) {
        super(customMessage);
    }
}