package com.falcon.andres.productos.controller;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;
import com.falcon.andres.productos.exception.ExceptionBadRequest;
import com.falcon.andres.productos.exception.ExceptionNotFound;
import com.falcon.andres.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> registrarListarProductos(@RequestBody @Valid ProductoRequestDto requestDto) {
        try {
            ProductoResponseDto responseDto = productoService.registrarListarProductos(requestDto);
            return ResponseEntity.ok(responseDto);
        }catch (DataAccessException dae){
            throw  new ExceptionBadRequest(dae.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        ProductoResponseDto responseDto = productoService.listar();
        if (responseDto.getProductos() == null ){
            throw new ExceptionNotFound("Productos");
        }
        return ResponseEntity.ok(productoService.listar());
    }
}
