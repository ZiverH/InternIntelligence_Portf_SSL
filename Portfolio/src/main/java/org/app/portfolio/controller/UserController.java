package org.app.portfolio.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.portfolio.dto.request.LoginRequestDto;
import org.app.portfolio.dto.request.UserDto;
import org.app.portfolio.model.User;
import org.app.portfolio.repository.UserRepository;
import org.app.portfolio.service.UserService;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name="User", description = "User API. Contains all operations that can be performed with user")
@Order(1)
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserDto userDto) {
        userService.register(userDto);
        return ResponseEntity.created(URI.create("user/")).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody @Valid LoginRequestDto requestDto) {

        return ResponseEntity.ok().body(userService.login(requestDto));
    }
}
