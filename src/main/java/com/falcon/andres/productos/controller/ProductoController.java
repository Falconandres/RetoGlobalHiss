package com.falcon.andres.productos.controller;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;
import com.falcon.andres.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> registrarListarProductos(@Valid @RequestBody ProductoRequestDto requestDto, BindingResult result) {

        if (result.hasErrors()){
            Map<String, Object> res = new HashMap<>();
            res.put("Validación", result.getAllErrors());
            return ResponseEntity.badRequest().body(res);
        }

        ProductoResponseDto responseDto = productoService.registrarListarProductos(requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(productoService.listar());
    }
}
