package com.falcon.andres.productos.controller;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;
import com.falcon.andres.productos.exception.ExceptionBadRequest;
import com.falcon.andres.productos.exception.ExceptionNotFound;
import com.falcon.andres.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registrarListarProductos(@RequestBody @Valid ProductoRequestDto requestDto) {
        try {
            ProductoResponseDto responseDto = productoService.registrarListarProductos(requestDto);
            return ResponseEntity.ok(responseDto);
        }catch (DataAccessException dae){
            throw  new ExceptionBadRequest(dae.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listar(){
        ProductoResponseDto responseDto = productoService.listar();
        if (responseDto.getProductos() == null ){
            throw new ExceptionNotFound("Productos");
        }
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/index")
    @PreAuthorize("hasAnyRole('ADMIN','INVITADO')")
    public ResponseEntity<?> index(){
        Map<String, String> response = new HashMap<>();
        response.put("Session","Sesion iniciada con exito !");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/debug")
    public ResponseEntity<?> debug() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }
}
