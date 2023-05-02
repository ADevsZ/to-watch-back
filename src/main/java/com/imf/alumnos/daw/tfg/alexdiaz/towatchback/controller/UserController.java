package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLoginDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public String test() {
        return "Hola Mundo!";
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@RequestBody UserLoginDto item) {
        Optional<User> user = userRepository.findByEmail(item.getEmail());

        if (user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> items = new ArrayList<>();

            userRepository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> existingItemOptional = userRepository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody UserDto item) {
        try {
            User user = new User();
            user.setEmail(item.getEmail());
            user.setFirstName(item.getFirstName());
            user.setLastName(item.getLastName());
            user.setLoginName(item.getLoginName());
            user.setPassword(item.getPassword());

            User savedItem = userRepository.save(user);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDto item) {
        Optional<User> existingItemOptional = userRepository.findById(id);
        if (existingItemOptional.isPresent()) {
            User existingItem = existingItemOptional.get();
            return new ResponseEntity<>(userRepository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}