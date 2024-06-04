package com.falcon.andres.productos.dto;

import com.falcon.andres.productos.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDto {
    private List<Producto> productos;
    private Integer codigo;
    private String mensaje;

}
