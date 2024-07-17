package com.tapianadia.forohub.services;

import com.tapianadia.forohub.dto.topic.TopicRequestDTO;
import com.tapianadia.forohub.dto.topic.TopicResponseDTO;
import com.tapianadia.forohub.entities.Course;
import com.tapianadia.forohub.entities.Status;
import com.tapianadia.forohub.entities.Topic;
import com.tapianadia.forohub.entities.User;
import com.tapianadia.forohub.repositories.CourseRepository;
import com.tapianadia.forohub.repositories.TopicRepository;
import com.tapianadia.forohub.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class TopicService implements IServices<TopicRequestDTO, TopicResponseDTO> {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TopicResponseDTO actualizar(Long id, TopicRequestDTO nuevosDatos) {
        User autor = userRepository.findById(nuevosDatos.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Course curso = courseRepository.findById(nuevosDatos.getCurso().getId()).orElseThrow(() -> new EntityNotFoundException());
        Topic topicoActualizado = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        topicoActualizado.setTitulo(nuevosDatos.getTitulo());
        topicoActualizado.setMensaje(nuevosDatos.getMensaje());
        topicoActualizado.setAutor(autor);
        topicoActualizado.setCurso(curso);
        return modelMapper.map(topicRepository.save(topicoActualizado), TopicResponseDTO.class);
    }

    public void eliminar(Long id) {
        Topic topico = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        topicRepository.delete(topico);
    }

    public TopicResponseDTO encontrar(Long id) {
        Topic topico = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(topico, TopicResponseDTO.class);
    }

    public TopicResponseDTO guardar(TopicRequestDTO nuevoRegistro) {
        User autor = userRepository.findById(nuevoRegistro.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Course curso = courseRepository.findById(nuevoRegistro.getCurso().getId()).orElseThrow(() -> new EntityNotFoundException());
        Topic topicoNuevo = modelMapper.map(nuevoRegistro, Topic.class);
        topicoNuevo.setStatus(Status.ACTIVO);
        topicoNuevo.setFechaCreacion(LocalDateTime.now());
        topicoNuevo.setAutor(autor);
        topicoNuevo.setCurso(curso);
        return modelMapper.map(topicRepository.save(topicoNuevo), TopicResponseDTO.class);
    }

    public List<TopicResponseDTO> listar() {
        // List<Topic> topicos = topicoRepository.encontrarTopicosActivos(Status.ACTIVO);
        List<Topic> topicos = topicRepository.findAll();
        List<TopicResponseDTO> listaTopicoResponseDTOs = topicos.stream().map(t -> modelMapper.map(t, TopicResponseDTO.class)).toList();
        return listaTopicoResponseDTOs;
    }
    // public List<TopicoResponseDTO> listarInactivos() {
    //     List<Topic> topicos = topicRepository.encontrarTopicosActivos(Status.INACTIVO);
    //     List<TopicoResponseDTO> listaTopicResponseDTOs = topicos.stream().map(t -> modelMapper.map(t, TopicResponseDTO.class)).toList();
    //     return listaTopicResponseDTOs;
    // }

    public List<TopicResponseDTO> listarAscendente() {
        List<Topic> topicos = topicRepository.findTop10ByOrderByFechaCreacionAsc();
        List<TopicResponseDTO> topicoResponseDTOs = topicos.stream().map(t -> modelMapper.map(t, TopicResponseDTO.class)).toList();
        return topicoResponseDTOs;
    }

    public Page<TopicResponseDTO> listarPorCursoYAnio(Pageable pageable, String curso, int anio){
        LocalDateTime inicioDelAnio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0, 0);
        LocalDateTime finDelAnio = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59, 59);
        Page<Topic> topicos = topicRepository.encontrarPorCursoYAnio(pageable, curso, inicioDelAnio, finDelAnio);
        return topicos.map(t -> modelMapper.map(t, TopicResponseDTO.class));
    }
}

