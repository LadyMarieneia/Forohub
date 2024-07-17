package com.tapianadia.forohub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IControllers <P, R> {


    ResponseEntity<List<R>> listar();

    ResponseEntity<R> encontrar(@PathVariable Long id);

    ResponseEntity<R> guardar(@RequestBody P nuevoRegistro);

    ResponseEntity<R> actualizar(@PathVariable Long id, @RequestBody P nuevosDatos);

    ResponseEntity<?> eliminar(@PathVariable Long id);

}
