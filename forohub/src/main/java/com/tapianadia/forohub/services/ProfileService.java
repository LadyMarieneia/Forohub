package com.tapianadia.forohub.services;

import com.tapianadia.forohub.dto.profile.ProfileRequestDTO;
import com.tapianadia.forohub.dto.profile.ProfileResponseDTO;
import com.tapianadia.forohub.entities.Profile;
import com.tapianadia.forohub.repositories.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements IServices <ProfileRequestDTO, ProfileResponseDTO>{

    @Autowired
    private ProfileRepository profileRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ProfileResponseDTO actualizar(Long id, ProfileRequestDTO nuevosDatos) {
        Profile perfil = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        perfil.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(profileRepository.save(perfil), ProfileResponseDTO.class);
    }

    public void eliminar(Long id) {
        Profile perfil = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        profileRepository.delete(perfil);
    }

    public ProfileResponseDTO encontrar(Long id) {
        Profile perfilEncontrado = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(perfilEncontrado, ProfileResponseDTO.class);
    }

    public ProfileResponseDTO guardar(ProfileRequestDTO nuevoRegistro) {
        Profile perfilNuevo = modelMapper.map(nuevoRegistro, Profile.class);
        return modelMapper.map(profileRepository.save(perfilNuevo), ProfileResponseDTO.class);
    }

    public List<ProfileResponseDTO> listar() {
        List<Profile> perfiles = profileRepository.findAll();
        List<ProfileResponseDTO> perfilesResponseDTOs = perfiles.stream().map(p -> modelMapper.map(p, ProfileResponseDTO.class)).toList();
        return perfilesResponseDTOs;
    }
}


