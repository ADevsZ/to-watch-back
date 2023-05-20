package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email); 
     
}
