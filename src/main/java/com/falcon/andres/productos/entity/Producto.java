package com.falcon.andres.productos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto implements Serializable {
    private Long id;
    private String nombre;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaRegistro;

}
