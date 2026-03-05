package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.status.Status;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        Topico topico,
        LocalDateTime fechaCreacion,
        Usuario autor,
        Status status) {
}
