package com.tapianadia.forohub.repositories;

import com.tapianadia.forohub.entities.Status;
import com.tapianadia.forohub.entities.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    @Query("select t from Topic t where t.status = :status")
    List<Topic> encontrarTopicosActivos(Status status);


    List<Topic> findTop10ByOrderByFechaCreacionAsc();

    @Query("SELECT t FROM Topic t JOIN t.curso c where c.nombre = :curso and t.fechaCreacion between :inicioDelAnio and :finDelAnio")
    Page<Topic> encontrarPorCursoYAnio(Pageable pageable, String curso, LocalDateTime inicioDelAnio, LocalDateTime finDelAnio);

}
