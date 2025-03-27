package com.rayco.presentation.controller;


import com.rayco.application.service.UserService;
import com.rayco.presentation.dto.LoginDTO;
import com.rayco.presentation.dto.UserCreateDTO;
import com.rayco.presentation.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginDTO dto){
        return service.login(dto);
    }

    @PostMapping("/register")
    public TokenResponse register(@RequestBody @Valid UserCreateDTO dto){
        return service.register(dto);
    }
}
