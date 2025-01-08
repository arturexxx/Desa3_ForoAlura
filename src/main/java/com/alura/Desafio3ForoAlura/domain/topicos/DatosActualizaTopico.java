package com.alura.Desafio3ForoAlura.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizaTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
}
