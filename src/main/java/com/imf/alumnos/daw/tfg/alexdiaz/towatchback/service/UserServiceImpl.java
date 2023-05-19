package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.User;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.UserLogs;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.UserLogsEnum;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLoginDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserLogsDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.dto.UserNickDto;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserLogsRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository.UserRepository;
import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.security.TokenUtils;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogsRepository userLogsRepository;

    @Override
    public long getUserIdByToken(String token) {
        UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthenticationToken(token);
        String email = usernamePAT.getName();
        Optional<User> optional = this.userRepository.findByEmail(email);
        return optional.isPresent() ? optional.get().getId() : 0;
    }

    @Override
    public Optional<User> userLogin(UserLoginDto userLoginDto) {
        return userRepository.findByEmail(userLoginDto.getEmail());
    }

    @Override
    public void registerUser(UserDto userDto, String token) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLoginName(userDto.getLoginName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));

        this.userRepository.save(user);

        Optional<User> userLog = this.userRepository.findByEmail(user.getEmail());
        if (userLog.isPresent()) {
            this.createUserLog(this.construirUserLog("TW01", UserLogsEnum.USUARIO_REGISTRADO.label + userLog.get().getLoginName(), new Date(), null, userLog.get().getId()));
        }
    }

    @Override
    public void updateDataUser(long userId, UserDto userDto, String token) {
        Optional<User> optional = userRepository.findById(userId);

        if (optional.isPresent()) {
            User user = optional.get();

            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(user.getPassword());

            this.userRepository.save(user);

            this.createUserLog(this.construirUserLog("TW02", UserLogsEnum.USUARIO_REGISTRADO.label + user.getLoginName(), new Date(), null, user.getId()));
        }
    }

    @Override
    public UserNickDto getLoginNameByToken(String token) {
        UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthenticationToken(token);
        String email = usernamePAT.getName();
        Optional<User> optional = this.userRepository.findByEmail(email);

        UserNickDto userNickDto = null;

        if (optional.isPresent()) {
            userNickDto = new UserNickDto();
            userNickDto.setNick(optional.get().getLoginName());
        }
        
        return userNickDto;
    }

    @Override
    public List<UserLogsDto> getAllUserLogs(long userId) {
        List<UserLogs> optional = this.userLogsRepository.getAllUserLogsById(userId);
        List<UserLogsDto> list = new ArrayList<>();

        for (UserLogs ul: optional) {
            UserLogsDto userLogsDto = new UserLogsDto(ul.getCode(), ul.getDescription(), ul.getCreationDate());
            list.add(userLogsDto);
        }

        return list;
    }

    @Override
    public UserLogs construirUserLog(String code, String description, Date creationDate, String token, long userId) {
        if (userId == 0) {
            userId = this.getUserIdByToken(token);
        }
        Optional<User> user = this.userRepository.findById(userId);
        long logId = this.userLogsRepository.count() + 1;
        UserLogs userLogs = null;
        if (user.isPresent()) {
            userLogs = new UserLogs(logId, code, description, creationDate, user.get());
        }
        return userLogs;
    }

    @Override
    public void createUserLog(UserLogs userLogs) {
        this.userLogsRepository.save(userLogs);
    }
}
