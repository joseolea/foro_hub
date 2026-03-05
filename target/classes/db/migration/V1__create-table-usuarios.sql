CREATE TABLE usuarios (
   id BIGSERIAL NOT NULL,
   nombre VARCHAR(100) NOT NULL,
   correo_electronico VARCHAR(100) NOT NULL,
   contrasena VARCHAR(255) NOT NULL,


   PRIMARY KEY (id)
);