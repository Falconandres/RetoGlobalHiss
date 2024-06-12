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

create table USUARIO(
    ID_USUARIO NUMBER(10,0) CONSTRAINT PK_USUARIO PRIMARY KEY,
    USERNAME VARCHAR2(50),
    PASSWORD VARCHAR2(200),
    ENABLED NUMBER(1)
);

CREATE TABLE ROL(
    ID_ROL NUMBER(10) CONSTRAINT PK_ROL PRIMARY KEY,
    USUARIO_ID NUMBER(10,0),
    CONSTRAINT FK_USUARIO FOREIGN KEY(USUARIO_ID) REFERENCES USUARIO(ID_USUARIO),
    ROL VARCHAR2(50)
);

/*LA CONTRASEÑA ES 1234*/
Insert into USUARIO (ID_USUARIO,USERNAME,PASSWORD,ENABLED) values ('1','FALCON','$2a$10$3mbnQEbL6aFwsiUgaNUnOeEzQ3LJhCtD3UYy/FYPKyYlqzfWY0e8y','1');
Insert into USUARIO (ID_USUARIO,USERNAME,PASSWORD,ENABLED) values ('2','USUARIO','$2a$10$fckA9DjCrWA7yXUtDoMGNOuRlxKHG3yZZmcDCvg6grZwiE584JjRS','1');

Insert into ROL (ID_ROL,USUARIO_ID,ROL) values ('1','1','ROLE_ADMIN');
Insert into ROL (ID_ROL,USUARIO_ID,ROL) values ('2','2','ROLE_INVITADO');






