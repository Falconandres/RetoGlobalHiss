package com.falcon.andres.productos.service.impl;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;
import com.falcon.andres.productos.repository.ProductoRepository;
import com.falcon.andres.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.RemoteException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    @Override
    public ProductoResponseDto registrarListarProductos(ProductoRequestDto requestDto)  {
        return productoRepository.registrarListarProductos(requestDto);
    }

    @Override
    public ProductoResponseDto listar() {
        return productoRepository.listar();
    }
}
