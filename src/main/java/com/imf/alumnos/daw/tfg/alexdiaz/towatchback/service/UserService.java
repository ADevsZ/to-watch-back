package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.UserLogs;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLoginDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLogsDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserNickDto;

public interface UserService {
    Optional<User> userLogin(UserLoginDto userLoginDto);
    void registerUser(UserDto userDto, String token);
    void updateDataUser(long userId, UserDto userDto, String token);
    UserNickDto getLoginNameByToken(String token);
    long getUserIdByToken(String token);
    List<UserLogsDto> getAllUserLogs(long userId);
    UserLogs construirUserLog(String code, String description, Date creationDate, String token, long userId);
    void createUserLog(UserLogs userLogs);
    UserDto getUserByToken(String token);
}
