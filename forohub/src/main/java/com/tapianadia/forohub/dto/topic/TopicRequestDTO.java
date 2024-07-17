package com.tapianadia.forohub.dto.topic;

import com.tapianadia.forohub.entities.Course;
import com.tapianadia.forohub.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequestDTO {

    private String titulo;
    private String mensaje;
    private User autor;
    private Course curso;

}
