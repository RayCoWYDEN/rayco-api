package com.rayco.presentation.controller;


import com.rayco.application.service.UserService;
import com.rayco.presentation.dto.LoginDTO;
import com.rayco.presentation.dto.UserCreateDTO;
import com.rayco.presentation.dto.UserInfoDTO;
import com.rayco.presentation.response.UserLoggedResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserLoggedResponse> login(@RequestBody @Valid LoginDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoggedResponse register(@RequestBody @Valid UserCreateDTO dto){
        return service.register(dto);
    }

    @PostMapping("personal-info")
    public ResponseEntity<?> saveUserInfo(@RequestBody @Valid UserInfoDTO dto){
        service.saveUserInfo(dto);
        return ResponseEntity.ok().build();
    }

}
