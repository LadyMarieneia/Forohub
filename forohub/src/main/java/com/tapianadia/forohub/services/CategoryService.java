package com.tapianadia.forohub.services;

import com.tapianadia.forohub.dto.category.CategoryRequestDTO;
import com.tapianadia.forohub.dto.category.CategoryResponseDTO;
import com.tapianadia.forohub.entities.Category;
import com.tapianadia.forohub.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IServices<CategoryRequestDTO, CategoryResponseDTO>{

    @Autowired
    private CategoryRepository categoryRepository;


    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoryResponseDTO actualizar(Long id, CategoryRequestDTO nuevosDatos) {
        Category categoria = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        categoria.setNombre(nuevosDatos.getNombre());

        return modelMapper.map(categoryRepository.save(categoria), CategoryResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        Category categoria = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        categoryRepository.delete(categoria);

    }

    @Override
    public CategoryResponseDTO encontrar(Long id) {
        Category categoriaEncontrada = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(categoriaEncontrada, CategoryResponseDTO.class);
    }

    @Override
    public CategoryResponseDTO guardar(CategoryRequestDTO nuevoRegistro) {
        Category nuevaCategoria = modelMapper.map(nuevoRegistro, Category.class);
        CategoryResponseDTO categoryResponseDTO = modelMapper.map(categoryRepository.save(nuevaCategoria), CategoryResponseDTO.class);
        return categoryResponseDTO;
    }

    @Override
    public List<CategoryResponseDTO> listar() {
        List<Category> lista = categoryRepository.findAll();
        List<CategoryResponseDTO> listaCategoriaResponseDTOs = lista.stream().map(c -> modelMapper.map(c, CategoryResponseDTO.class)).toList();
        return listaCategoriaResponseDTOs;
    }

}


