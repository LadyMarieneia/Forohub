package com.tapianadia.forohub.controllers;

import com.tapianadia.forohub.dto.topic.TopicRequestDTO;
import com.tapianadia.forohub.dto.topic.TopicResponseDTO;
import com.tapianadia.forohub.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topico")
public class TopicController implements IControllers<TopicRequestDTO, TopicResponseDTO> {

    @Autowired
    private TopicService topicService;

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicResponseDTO> actualizar(Long id, TopicRequestDTO nuevosDatos) {
        return new ResponseEntity<>(topicService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        topicService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TopicResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(topicService.encontrar(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TopicResponseDTO> guardar(TopicRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(topicService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<TopicResponseDTO>> listar() {
        return new ResponseEntity<>(topicService.listar(), HttpStatus.OK);
    }

    @GetMapping("/find/ten/asc")
    public ResponseEntity<List<TopicResponseDTO>> listarAsc() {
        return new ResponseEntity<>(topicService.listarAscendente(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<TopicResponseDTO>> listarPorCursoYAnio(@PageableDefault(size = 1) Pageable pageable, @RequestParam("curso") String curso, @RequestParam("anio") int anio) {
        return new ResponseEntity<>(topicService.listarPorCursoYAnio(pageable, curso, anio), HttpStatus.OK);
    }
}
