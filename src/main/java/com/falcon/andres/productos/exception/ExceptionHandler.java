package com.falcon.andres.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    //controla los errores de los campos
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlderMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                          WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    mapErrors.put(clave, valor);
                }
        );
        mapErrors.put("url", webRequest.getDescription(false).replace("uri=",""));
        return new ResponseEntity<>(mapErrors, HttpStatus.BAD_REQUEST);
    }

    //controla los errores de logica o de los catch en general 400
    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionBadRequest.class)
    public ResponseEntity<?> handlerBadRequestException(ExceptionBadRequest exception,
                                                                  WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();
        mapErrors.put("Error", exception.getMessage());
        mapErrors.put("url", webRequest.getDescription(false).replace("uri=",""));
        return new ResponseEntity<>(mapErrors, HttpStatus.BAD_REQUEST);
    }

    //controla los errores de varios tipos y globalizrlo con un error 500
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception exception,
                                                        WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();
        mapErrors.put("Error", exception.getMessage());
        mapErrors.put("url", webRequest.getDescription(false).replace("uri=",""));
        return new ResponseEntity<>(mapErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
