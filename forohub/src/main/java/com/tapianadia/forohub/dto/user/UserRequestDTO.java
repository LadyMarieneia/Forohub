package com.tapianadia.forohub.dto.user;

import com.tapianadia.forohub.entities.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotNull
    private String nombre;
    @Email
    private String correoElectronico;
    @NotNull
    @NotBlank
    private String contrasena;
    @NotNull
    private Profile perfil;
}
