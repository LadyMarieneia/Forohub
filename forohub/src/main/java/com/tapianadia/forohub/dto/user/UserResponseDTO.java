package com.tapianadia.forohub.dto.user;

import com.tapianadia.forohub.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private Profile perfil;
}
