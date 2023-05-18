package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.security.TokenUtils;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public long getUserIdByToken(String token) {
        UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthenticationToken(token);
        String email = usernamePAT.getName();
        Optional<User> optional = this.userRepository.findByEmail(email);
        return optional.isPresent() ? optional.get().getId() : 0;
    }
}
