package com.tapianadia.forohub.dto.response;

import com.tapianadia.forohub.entities.Topic;
import com.tapianadia.forohub.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRequestDTO {

    @NotBlank
    private String mensaje;
    @NotNull
    private Topic topico;
    @NotNull
    private User autor;

}
