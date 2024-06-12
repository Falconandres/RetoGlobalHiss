package com.falcon.andres.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExceptionBadRequest extends RuntimeException{
    public ExceptionBadRequest(String mensaje) {
        super(mensaje);
    }
}
