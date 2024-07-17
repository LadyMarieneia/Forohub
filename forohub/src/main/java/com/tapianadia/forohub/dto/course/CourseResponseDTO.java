package com.tapianadia.forohub.dto.course;

import com.tapianadia.forohub.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String nombre;
    private Category categoria;

}

