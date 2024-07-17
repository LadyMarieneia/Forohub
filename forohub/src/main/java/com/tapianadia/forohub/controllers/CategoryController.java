package com.tapianadia.forohub.controllers;

import com.tapianadia.forohub.dto.category.CategoryRequestDTO;
import com.tapianadia.forohub.dto.category.CategoryResponseDTO;
import com.tapianadia.forohub.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoryController implements IControllers<CategoryRequestDTO, CategoryResponseDTO> {

    @Autowired
    private CategoryService categoryService;

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> actualizar(Long id, CategoryRequestDTO nuevosDatos) {
        return new ResponseEntity<>(categoryService.actualizar(id, nuevosDatos), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        categoryService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryResponseDTO> encontrar(Long id) {
        return new ResponseEntity<>(categoryService.encontrar(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> guardar(CategoryRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(categoryService.guardar(nuevoRegistro), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CategoryResponseDTO>> listar() {
        return new ResponseEntity<>(categoryService.listar(), HttpStatus.OK);
    }

}

