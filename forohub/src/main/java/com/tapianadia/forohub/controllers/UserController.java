package com.tapianadia.forohub.controllers;

import com.tapianadia.forohub.dto.user.UserRequestDTO;
import com.tapianadia.forohub.dto.user.UserResponseDTO;
import com.tapianadia.forohub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController implements IControllers<UserRequestDTO, UserResponseDTO> {

    @Autowired
    private UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> actualizar(Long id, UserRequestDTO nuevosDatos) {
        return new ResponseEntity<>(userService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(userService.encontrar(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> guardar(UserRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(userService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<UserResponseDTO>> listar() {
        return new ResponseEntity<>(userService.listar(), HttpStatus.OK);
    }
}
