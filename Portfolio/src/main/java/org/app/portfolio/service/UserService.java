package org.app.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.app.portfolio.config.BaseJwtService;
import org.app.portfolio.dto.request.LoginRequestDto;
import org.app.portfolio.dto.request.UserDto;
import org.app.portfolio.exception.NotFoundException;
import org.app.portfolio.model.User;
import org.app.portfolio.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BaseJwtService baseJwtService;


    public void register(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        User user = User.builder().
                name(userDto.getName()).
                surname(userDto.getSurname()).
                password(passwordEncoder.encode(userDto.getPassword())).
                email(userDto.getEmail()).
                build();

        userRepository.save(user);
    }

    public Map<String,String> login(LoginRequestDto requestDto) {

        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(()->new NotFoundException(User.class.getSimpleName()));
        boolean matches = passwordEncoder.matches(requestDto.getPassword(), user.getPassword());
        if(!matches) {
            throw new NotFoundException(User.class.getSimpleName());
        }
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken",baseJwtService.accessToken(user));
        tokens.put("refreshToken",baseJwtService.refreshToken(user));

        return tokens;

    }
}
