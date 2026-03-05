package com.alura.forohub.controller;


import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        var autor = usuarioRepository.getReferenceById(datos.idUsuario());
        var topico = new Topico(datos, autor);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}")
                        .buildAndExpand(topico.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaTopico(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"})Pageable paginacion){
        var page = topicoRepository.findAll(paginacion)
                .map(DatosDetalleTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos){

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        if (datos.titulo() != null && !datos.titulo().equals(topico.getTitulo())) {

            if (topicoRepository.existsByTituloIgnoreCase(datos.titulo())) {
                return ResponseEntity.badRequest().body("ERROR: Ese título ya existe en el sistema.");
            }
        }

        if (datos.mensaje() != null && !datos.mensaje().equals(topico.getMensaje())) {
            if (topicoRepository.existsByMensajeIgnoreCase(datos.mensaje())) {
                return ResponseEntity.badRequest().body("ERROR: Ese mensaje ya existe en el sistema.");
            }
        }
        topico.actualizarTopico(datos);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
