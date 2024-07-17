package com.tapianadia.forohub.repositories;

import com.tapianadia.forohub.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
