package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    @Query("select count(t) > 0 from Topico t where lower(t.titulo) = lower(:titulo)")
    boolean existsByTituloIgnoreCase(String titulo);
    @Query("select count(t) > 0 from Topico t where lower(t.mensaje) = lower(:mensaje)")
    boolean existsByMensajeIgnoreCase(String mensaje);
}
