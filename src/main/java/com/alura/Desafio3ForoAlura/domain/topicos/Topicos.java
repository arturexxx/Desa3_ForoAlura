package com.alura.Desafio3ForoAlura.domain.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechacreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;


    public Topicos(DatosRegistroTopico datosRegistrotopico) {
        this.titulo = datosRegistrotopico.titulo();
        this.mensaje = datosRegistrotopico.mensaje();
        this.fechacreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;
        this.autor = datosRegistrotopico.autor();
        this.curso = datosRegistrotopico.curso();
    }

    public void actualizarDatos(DatosActualizaTopico datosActualizaTopico) {
        if (datosActualizaTopico.titulo() != null) {
            this.titulo = datosActualizaTopico.titulo();
        }
        if (datosActualizaTopico.mensaje() != null) {
            this.mensaje = datosActualizaTopico.mensaje();
        }
        if (datosActualizaTopico.autor() != null) {
            this.autor = datosActualizaTopico.autor();
        }
        if (datosActualizaTopico.curso() != null) {
            this.curso = datosActualizaTopico.curso();
        }
    }


}
