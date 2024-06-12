package com.falcon.andres.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExceptionNotFound extends RuntimeException{
    private String mensaje;
    public ExceptionNotFound(String mensaje) {
        super(String.format("No hay registros de %s en el sistema.", mensaje ));
        this.mensaje = mensaje;
    }
}
