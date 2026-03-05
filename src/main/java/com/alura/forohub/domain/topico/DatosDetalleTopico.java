package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.status.Status;
import com.alura.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autor,
        String curso
) {

    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getNombreCurso()
        );
    }
}
