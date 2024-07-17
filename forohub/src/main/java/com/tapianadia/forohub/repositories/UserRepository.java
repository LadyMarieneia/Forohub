package com.tapianadia.forohub.repositories;


import com.tapianadia.forohub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNombre(String username);

}
