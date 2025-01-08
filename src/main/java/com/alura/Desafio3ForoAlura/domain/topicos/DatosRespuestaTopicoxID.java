package com.alura.Desafio3ForoAlura.domain.topicos;

import java.time.LocalDateTime;

public record DatosRespuestaTopicoxID(
        String titulo,
        String Mensaje,
        LocalDateTime fechaCreacion,
        String curso,
        String Status,
        String Autor
        ){}
