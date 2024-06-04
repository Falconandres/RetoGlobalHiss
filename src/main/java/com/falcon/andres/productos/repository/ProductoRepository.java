package com.falcon.andres.productos.repository;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;

public interface ProductoRepository{

    public ProductoResponseDto registrarListarProductos(ProductoRequestDto requestDto);
    public ProductoResponseDto listar();
}
