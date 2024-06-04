package com.falcon.andres.productos.repository;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ProductoRepository{

    public ProductoResponseDto registrarListarProductos(ProductoRequestDto requestDto);
    public ProductoResponseDto listar();
}
