package com.falcon.andres.productos.repository.impl;

import com.falcon.andres.productos.dto.ProductoRequestDto;
import com.falcon.andres.productos.dto.ProductoResponseDto;
import com.falcon.andres.productos.entity.Producto;
import com.falcon.andres.productos.repository.ProductoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private final EntityManager entityManager;

    public ProductoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductoResponseDto registrarListarProductos(ProductoRequestDto requestDto) {

        ProductoResponseDto responseDto = new ProductoResponseDto();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("FALCON.PKG_PRODUCTO.REGISTRAR_LISTAR_PRODUCTO");
        storedProcedureQuery.registerStoredProcedureParameter("idProducto", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("nombreProducto", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("fechaRegistro", String.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter("productosCursor", Void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.registerStoredProcedureParameter("codigo", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("idProducto", requestDto.getIdProducto());
        storedProcedureQuery.setParameter("nombreProducto", requestDto.getNombreProducto());
        storedProcedureQuery.setParameter("fechaRegistro", requestDto.getFechaRegistro());

        try {
            storedProcedureQuery.execute();
            List<Object[]> resultList = storedProcedureQuery.getResultList();
            List<Producto> productos = new ArrayList<>();
            for (Object[] result : resultList){
                Producto producto = new Producto();
                producto.setId(((BigDecimal) result[0]).longValue());
                producto.setNombre((String)result[1]);
                producto.setFechaRegistro((Date) result[2]);
                productos.add(producto);
            }

            Integer codigoSalida = (Integer) storedProcedureQuery.getOutputParameterValue("codigo");
            String mensajeSalida = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
            if (codigoSalida == 1) {
                responseDto = ProductoResponseDto.builder().codigo(codigoSalida).mensaje(mensajeSalida).build();
            }else{
                responseDto = ProductoResponseDto.builder().codigo(codigoSalida).mensaje(mensajeSalida).productos(productos).build();
            }
            return responseDto;
        }catch (Exception e){
            Integer codigoSalida = (Integer) storedProcedureQuery.getOutputParameterValue("codigo");
            String mensajeSalida = (String) storedProcedureQuery.getOutputParameterValue("mensaje");
            responseDto = ProductoResponseDto.builder().codigo(codigoSalida).mensaje(mensajeSalida).build();
            return responseDto;
        }


    }

    @Override
    public ProductoResponseDto listar() {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("FALCON.PKG_PRODUCTO.LISTAR_PRODUCTO");
        storedProcedureQuery.registerStoredProcedureParameter("productosCursor", Void.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.execute();

        List<Object[]> resultList = storedProcedureQuery.getResultList();
        ProductoResponseDto responseDto = new ProductoResponseDto();
        List<Producto> productos = new ArrayList<>();

        for (Object[] result : resultList){
            Producto producto = new Producto();
            producto.setId(((BigDecimal) result[0]).longValue());
            producto.setNombre((String)result[1]);
            producto.setFechaRegistro((Date) result[2]);
            productos.add(producto);
            responseDto.setProductos(productos);
        }

        return responseDto;
    }
}
