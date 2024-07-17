package com.tapianadia.forohub.dto.category;

import com.tapianadia.forohub.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private String nombre;
    private Category categoria;
}
