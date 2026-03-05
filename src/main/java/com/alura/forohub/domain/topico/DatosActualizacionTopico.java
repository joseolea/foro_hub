package com.alura.forohub.domain.topico;


import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        String titulo,
        String mensaje,
        String nombreCurso
) {
}
