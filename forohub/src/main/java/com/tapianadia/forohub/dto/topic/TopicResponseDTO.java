package com.tapianadia.forohub.dto.topic;

import com.tapianadia.forohub.entities.Course;
import com.tapianadia.forohub.entities.Response;
import com.tapianadia.forohub.entities.Status;
import com.tapianadia.forohub.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDTO {

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Status status;
    private User autor;
    private Course curso;
    private List<Response> respuestas;

}
