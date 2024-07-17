package com.tapianadia.forohub.dto.response;

import com.tapianadia.forohub.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResponseDTO {

    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private User autor;

}
