package com.falcon.andres.productos.service;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;

public interface ProductoService {

    public ProductoResponseDto registrarListarProductos(ProductoRequestDto requestDto);
    public ProductoResponseDto listar();
}
