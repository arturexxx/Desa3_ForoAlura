package com.alura.Desafio3ForoAlura.domain.topicos;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String Mensaje,
        LocalDateTime fechaCreacion,
        String curso,
        String status,
        String autor
        ){}
