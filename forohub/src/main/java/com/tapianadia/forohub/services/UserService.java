package com.tapianadia.forohub.services;

import com.tapianadia.forohub.dto.user.UserRequestDTO;
import com.tapianadia.forohub.dto.user.UserResponseDTO;
import com.tapianadia.forohub.entities.Profile;
import com.tapianadia.forohub.entities.User;
import com.tapianadia.forohub.repositories.ProfileRepository;
import com.tapianadia.forohub.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IServices<UserRequestDTO, UserResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public UserResponseDTO actualizar(Long id, UserRequestDTO nuevosDatos) {
        User usuario = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Profile perfil = profileRepository.findById(nuevosDatos.getPerfil().getId()).orElseThrow(() -> new EntityNotFoundException());
        usuario.setContrasena(passwordEncoder.encode(nuevosDatos.getContrasena()));
        usuario.setCorreoElectronico(nuevosDatos.getCorreoElectronico());
        usuario.setNombre(nuevosDatos.getNombre());
        usuario.setPerfil(perfil);
        return modelMapper.map(userRepository.save(usuario), UserResponseDTO.class);
    }

    public void eliminar(Long id) {
        User usuario = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        userRepository.delete(usuario);
    }

    public UserResponseDTO encontrar(Long id) {
        User usuario = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(usuario, UserResponseDTO.class);
    }

    public UserResponseDTO guardar(UserRequestDTO nuevoRegistro) {
        User usuario = modelMapper.map(nuevoRegistro, User.class);
        usuario.setContrasena(passwordEncoder.encode(nuevoRegistro.getContrasena()));
        return modelMapper.map(userRepository.save(usuario), UserResponseDTO.class);
    }

    public List<UserResponseDTO> listar() {
        List<User> usuarios = userRepository.findAll();
        List<UserResponseDTO> usuarioResponseDTOs = usuarios.stream().map(u -> modelMapper.map(u, UserResponseDTO.class)).toList();
        return usuarioResponseDTOs;
    }




}
