create or replace PACKAGE PKG_PRODUCTO AS 

/***************************************************************************
Descripcion       : Registra y lista los productos
Fecha Creacion    : 04/06/2024
Autor             : Falcon Andres
***************************************************************************/
PROCEDURE REGISTRAR_LISTAR_PRODUCTO(
        idProducto IN NUMBER,
        nombreProducto IN VARCHAR2,
        fechaRegistro IN VARCHAR2,
        productosCursor OUT SYS_REFCURSOR,
        codigo OUT NUMBER,
        mensaje OUT VARCHAR2);

/***************************************************************************
Descripcion       : Lista los productos
Fecha Creacion    : 04/06/2024
Autor             : Falcon Andres
***************************************************************************/        
PROCEDURE LISTAR_PRODUCTO (productosCursor OUT SYS_REFCURSOR);        

END PKG_PRODUCTO;


create or replace PACKAGE BODY PKG_PRODUCTO AS

    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);
    w_filas    NUMBER := 1000;

    /***************************************************************************
    Descripcion       : Registra y lista los productos
    Fecha Creacion    : 04/06/2024
    Autor             : Falcon Andres
    ***************************************************************************/
    PROCEDURE registrar_listar_producto (
        idproducto      IN NUMBER,
        nombreproducto  IN VARCHAR2,
        fecharegistro   IN VARCHAR2,
        productoscursor OUT SYS_REFCURSOR,
        codigo          OUT NUMBER,
        mensaje         OUT VARCHAR2
    ) AS
    
        v_query      VARCHAR2(1000);
        v_resultados SYS_REFCURSOR;
        ln_cantidad  NUMBER(10);
    BEGIN
      v_query := 'SELECT ID_PRODUCTO, NOMBRE_PRODUCTO, FECHA_REGISTRO FROM PRODUCTO 
                WHERE TRUNC(FECHA_REGISTRO) = TO_DATE('''
                       || fecharegistro
                       || ''' , '''
                       || 'DD/MM/YYYY'
                       || ''') ORDER BY ID_PRODUCTO DESC';
      BEGIN  
      INSERT INTO producto VALUES (
                idproducto,
                nombreproducto,
                TO_DATE(fecharegistro, 'DD/MM/YYYY')
            );
            OPEN v_resultados FOR v_query;    
            productoscursor := v_resultados;
            mensaje := 'Ejecucion con exito!';
            codigo := 0;
      EXCEPTION
        WHEN OTHERS THEN
            OPEN v_resultados FOR v_query;    
            productoscursor := v_resultados;
            ln_sqlcode := sqlcode;
            ls_sqlerrm := substr(sqlerrm, 1, 250);
            mensaje := 'ERROR REGISTRAR_LISTAR_PRODUCTO: ('|| ln_sqlcode|| ') '|| ls_sqlerrm;
            codigo := 1;       
     END;

    EXCEPTION
        WHEN OTHERS THEN
            ln_sqlcode := sqlcode;
            ls_sqlerrm := substr(sqlerrm, 1, 250);
            mensaje := ln_sqlcode;
            codigo := 1;
            raise_application_error(-20101, 'ERROR REGISTRAR_LISTAR_PRODUCTO: ('
                                            || ln_sqlcode
                                            || ')'
                                            || ls_sqlerrm);
    END registrar_listar_producto;
    
    
    /***************************************************************************
    Descripcion       : Lista los productos
    Fecha Creacion    : 04/06/2024
    Autor             : Falcon Andres
    ***************************************************************************/ 
    PROCEDURE LISTAR_PRODUCTO(productosCursor OUT SYS_REFCURSOR) AS
    
    v_query      VARCHAR2(1000);
        v_resultados SYS_REFCURSOR;
    
    BEGIN
    v_query := 'SELECT ID_PRODUCTO, NOMBRE_PRODUCTO, FECHA_REGISTRO FROM PRODUCTO ORDER BY ID_PRODUCTO DESC';
            OPEN v_resultados FOR v_query;    
            productoscursor := v_resultados;
    END LISTAR_PRODUCTO;

END PKG_PRODUCTO;
