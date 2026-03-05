package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.DatosCurso;
import com.alura.forohub.domain.respuesta.DatosRespuesta;
import com.alura.forohub.domain.status.Status;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.SIN_RESPUESTA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String nombreCurso;
    private String respuestas;

    public Topico(DatosRegistroTopico datos, Usuario autor) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.nombreCurso = datos.nombreCurso();
        this.autor = autor;
    }

    public void actualizarTopico(DatosActualizacionTopico datos) {

        if (datos.titulo() != null && !datos.titulo().isBlank()) {
            this.titulo = datos.titulo();
        }

        if (datos.mensaje() != null && !datos.mensaje().isBlank()) {
            this.mensaje = datos.mensaje();
        }

        if (datos.nombreCurso() != null && !datos.nombreCurso().isBlank()) {
            this.nombreCurso = datos.nombreCurso();
        }
    }

}



