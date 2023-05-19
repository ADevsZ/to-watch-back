package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLoginDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLogsDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserNickDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@RequestBody UserLoginDto item) {
        try {
            Optional<User> user = userService.userLogin(item);

            if (user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDto item, String token) {
        try {
            this.userService.registerUser(item, token);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody UserDto item, String token) {
        try  {
            this.userService.updateDataUser(id, item, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nick")
    public ResponseEntity<UserNickDto> getUserByToken(@RequestParam("token") String token) {
        try {
            UserNickDto userNickDto = this.userService.getLoginNameByToken(token);
            return new ResponseEntity<>(userNickDto, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/{userId}/logs")
    // public ResponseEntity<List<UserLogsDto>> getAllUserLogs(@PathVariable("userId") long userId) {
    //     try {
    //         List<UserLogsDto> list = this.userService.getAllUserLogs(userId);
    //         return new ResponseEntity<>(list, HttpStatus.OK);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}