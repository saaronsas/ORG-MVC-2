package com.dev.springboot.exception;

public class SerieNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SerieNotFoundException() {
        super();
    }

    /**
     * Pasa al constructor el custom error
     * @param customMessage mensaje de error para mostrar
     */
    public SerieNotFoundException(String customMessage) {
        super(customMessage);
    }
}