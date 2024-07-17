package com.tapianadia.forohub.services;

import com.tapianadia.forohub.dto.course.CourseRequestDTO;
import com.tapianadia.forohub.dto.course.CourseResponseDTO;
import com.tapianadia.forohub.entities.Category;
import com.tapianadia.forohub.entities.Course;
import com.tapianadia.forohub.repositories.CategoryRepository;
import com.tapianadia.forohub.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements IServices<CourseRequestDTO, CourseResponseDTO> {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CourseResponseDTO actualizar(Long id, CourseRequestDTO nuevosDatos) {
        Course curso = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Category categoria = categoryRepository.findById(nuevosDatos.getCategoria().getId()).orElseThrow(() -> new EntityNotFoundException());
        curso.setCategoria(categoria);
        curso.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(courseRepository.save(curso), CourseResponseDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        Course curso = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        courseRepository.delete(curso);
    }

    @Override
    public CourseResponseDTO encontrar(Long id) {
        Course cursoEncontrado = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(cursoEncontrado, CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO guardar(CourseRequestDTO nuevoRegistro) {
        Course cursoNuevo = modelMapper.map(nuevoRegistro, Course.class);
        return modelMapper.map(courseRepository.save(cursoNuevo), CourseResponseDTO.class);
    }

    @Override
    public List<CourseResponseDTO> listar() {
        List<Course> cursos = courseRepository.findAll();
        List<CourseResponseDTO> cursoResponseDTOs = cursos.stream().map(c -> modelMapper.map(c, CourseResponseDTO.class)).toList();
        return cursoResponseDTOs;
    }


}


