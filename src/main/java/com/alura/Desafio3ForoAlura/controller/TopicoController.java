package com.alura.Desafio3ForoAlura.controller;


import com.alura.Desafio3ForoAlura.domain.topicos.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new IllegalArgumentException("Ya existe, el tópico con el mismo título y mensaje ingresado.");
        }
        Topicos topicos = topicoRepository.save(new Topicos(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getFechacreacion(),
                topicos.getStatus().toString(),
                topicos.getCurso(),
                topicos.getCurso()
        );
        URI url = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topicos.getId())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

/*    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        Page<Topicos> paginaDeTopicos = topicoRepository.findAll(paginacion);
        Page<DatosListadoTopico> paginaDeDatosListadoTopico = paginaDeTopicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(paginaDeDatosListadoTopico);
    }*/

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechacreacion",
                    direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<Topicos> paginaDeTopicos;
        if (curso != null && anio != null) {
            paginaDeTopicos = topicoRepository.findByCursoAndAnio(curso, anio, paginacion);
        } else if (curso != null) {
            paginaDeTopicos = topicoRepository.findByCurso(curso, paginacion);
        } else if (anio != null) {
            paginaDeTopicos = topicoRepository.findByAnio(anio, paginacion);
        } else {
            paginaDeTopicos = topicoRepository.findAll(paginacion);
        }
        Page<DatosListadoTopico> paginaDeDatosListadoTopico = paginaDeTopicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(paginaDeDatosListadoTopico);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopicoxID> retornaDatosTopico(@PathVariable Long id) {
        System.out.println("id ::" + id);
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
            Topicos topico = topicoRepository.getReferenceById(id);
            var datosTopico = new DatosRespuestaTopicoxID(
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechacreacion(),
                    topico.getCurso(),
                    topico.getStatus().toString(),
                    topico.getAutor()
            );
            return ResponseEntity.ok(datosTopico);
    }



    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @RequestBody @Valid DatosActualizaTopico datosActualizaTopico) {
        var optionalTopico = topicoRepository.findById(datosActualizaTopico.id());
        if (!optionalTopico.isPresent()) {
            throw new IllegalArgumentException("El tópico con el ID ingresado para actualizar NO Existe.");
        }
        Topicos topico = optionalTopico.get();
        topico.actualizarDatos(datosActualizaTopico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechacreacion(),
                topico.getCurso(),
                topico.getStatus().toString(),
                topico.getAutor()
        );
        return ResponseEntity.ok(datosRespuestaTopico);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity EliminarTopico(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);
        if (!optionalTopico.isPresent()) {
            throw new IllegalArgumentException("El tópico con el ID ingresado para eliminar NO existe.");
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
