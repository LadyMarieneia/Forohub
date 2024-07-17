package com.tapianadia.forohub.services;

import java.util.List;

public interface IServices <P, R> {



    List<R> listar();

    R encontrar(Long id);

    R guardar( P nuevoRegistro);

    R actualizar(Long id, P nuevosDatos);

    void eliminar(Long id);
}
