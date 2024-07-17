package com.tapianadia.forohub.services;

import com.tapianadia.forohub.dto.response.ResponseRequestDTO;
import com.tapianadia.forohub.dto.response.ResponseResponseDTO;
import com.tapianadia.forohub.entities.Response;
import com.tapianadia.forohub.entities.Topic;
import com.tapianadia.forohub.entities.User;
import com.tapianadia.forohub.repositories.ResponseRepository;
import com.tapianadia.forohub.repositories.TopicRepository;
import com.tapianadia.forohub.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResponseService implements IServices<ResponseRequestDTO, ResponseResponseDTO> {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseResponseDTO actualizar(Long id, ResponseRequestDTO nuevosDatos) {
        Topic topico = topicRepository.findById(nuevosDatos.getTopico().getId()).orElseThrow(() -> new EntityNotFoundException());
        User autor = userRepository.findById(nuevosDatos.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Response respuesta = responseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        respuesta.setMensaje(nuevosDatos.getMensaje());
        return modelMapper.map(responseRepository.save(respuesta), ResponseResponseDTO.class);
    }

    public void eliminar(Long id) {
        Response respuesta = responseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        responseRepository.delete(respuesta);
    }

    public ResponseResponseDTO encontrar(Long id) {
        Response respuesta = responseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(respuesta, ResponseResponseDTO.class);
    }

    public ResponseResponseDTO guardar(ResponseRequestDTO nuevoRegistro) {
        Topic topico = topicRepository.findById(nuevoRegistro.getTopico().getId()).orElseThrow(() -> new EntityNotFoundException());
        User autor = userRepository.findById(nuevoRegistro.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        Response respuesta = modelMapper.map(nuevoRegistro, Response.class);
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);
        respuesta.setFechaCreacion(LocalDateTime.now());
        return modelMapper.map(responseRepository.save(respuesta), ResponseResponseDTO.class);
    }

    public List<ResponseResponseDTO> listar() {
        List<Response> respuestas = responseRepository.findAll();
        List<ResponseResponseDTO> respuestaResponseDTOs = respuestas.stream().map(r -> modelMapper.map(r, ResponseResponseDTO.class)).toList();
        return respuestaResponseDTOs;
    }
}
