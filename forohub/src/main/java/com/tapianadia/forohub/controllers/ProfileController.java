package com.tapianadia.forohub.controllers;

import com.tapianadia.forohub.dto.profile.ProfileRequestDTO;
import com.tapianadia.forohub.dto.profile.ProfileResponseDTO;
import com.tapianadia.forohub.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class ProfileController implements IControllers<ProfileRequestDTO, ProfileResponseDTO> {

    @Autowired
    private ProfileService profileService;

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileResponseDTO> actualizar(Long id, ProfileRequestDTO nuevosDatos) {
        return new ResponseEntity<>(profileService.actualizar(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        profileService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProfileResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(profileService.encontrar(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDTO> guardar(ProfileRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(profileService.guardar(nuevoRegistro), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ProfileResponseDTO>> listar() {
        return new ResponseEntity<>(profileService.listar(), HttpStatus.OK);
    }
}
