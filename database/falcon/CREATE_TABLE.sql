/***************************************************************************
Descripcion       : Crear tabla producto
Fecha Creacion    : 04/06/2024
Autor             : Falcon Andres
***************************************************************************/

CREATE TABLE PRODUCTO (
    ID_PRODUCTO NUMBER(10,0) CONSTRAINT PK_PRODUCTO PRIMARY KEY,
    NOMBRE_PRODUCTO VARCHAR2(25),
    FECHA_REGISTRO DATE
);


