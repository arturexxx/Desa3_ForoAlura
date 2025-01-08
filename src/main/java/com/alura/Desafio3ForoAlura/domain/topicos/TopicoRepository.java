package com.alura.Desafio3ForoAlura.domain.topicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoRepository extends JpaRepository<Topicos, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(t.fechacreacion) = :anio")
    Page<Topicos> findByCursoAndAnio(String curso, Integer anio, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso")
    Page<Topicos> findByCurso(String curso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechacreacion) = :anio")
    Page<Topicos> findByAnio(Integer anio, Pageable pageable);

}
