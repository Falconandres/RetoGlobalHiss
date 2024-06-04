package com.falcon.andres.productos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoRequestDto {

    @NotNull(message = "El Id del producto no puede estar vacío")
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombreProducto;

    @NotBlank(message = "la fecha de registro no puede estar vacío")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/mm/yyyy")
    private String fechaRegistro;

}
