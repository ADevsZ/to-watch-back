package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByEmail(String email);    
}
