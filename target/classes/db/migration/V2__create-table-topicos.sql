create table topicos (
    id bigserial not null,
    titulo varchar(100) not null unique,
    mensaje text not null unique,
    fecha_creacion timestamp not null,
    status varchar(50) not null,
    autor_id bigint not null,
    nombre_curso varchar(100) not null,
    respuestas varchar(100),

    primary key(id),
    constraint fk_topicos_autor foreign key (autor_id) references usuarios(id)
);
