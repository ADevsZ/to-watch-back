package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String loginName;
    private String password;
    private String email;
}
