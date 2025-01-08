package com.alura.Desafio3ForoAlura.domain.topicos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DatosListadoTopico(
        String titulo,
        String mensaje,
        String fechacreacion,
        String status,
        String autor,
        String curso) {

    public DatosListadoTopico(Topicos topico) {
        this(topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechacreacion() != null
                        ? topico.getFechacreacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        : null,
                topico.getStatus().toString(),
                topico.getAutor(),
                topico.getCurso());
    }
}
