package com.tapianadia.forohub.controllers;

import com.tapianadia.forohub.dto.response.ResponseRequestDTO;
import com.tapianadia.forohub.dto.response.ResponseResponseDTO;
import com.tapianadia.forohub.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuesta")
public class ResponseController implements IControllers<ResponseRequestDTO, ResponseResponseDTO> {

    @Autowired
    private ResponseService respuestaService;

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseResponseDTO> actualizar(Long id, ResponseRequestDTO nuevosDatos) {
        return new ResponseEntity<>(respuestaService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        respuestaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(respuestaService.encontrar(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseResponseDTO> guardar(ResponseRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(respuestaService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<ResponseResponseDTO>> listar() {
        return new ResponseEntity<>(respuestaService.listar(), HttpStatus.OK);
    }




}

