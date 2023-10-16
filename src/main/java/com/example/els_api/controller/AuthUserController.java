package com.example.els_api.controller;

import com.example.els_api.domains.AuthUser;
import com.example.els_api.dto.AuthUserCreatedDTO;
import com.example.els_api.dto.LoginDTO;
import com.example.els_api.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody AuthUserCreatedDTO dto) {
        return new ResponseEntity<>(authUserService.login(dto), HttpStatus.CREATED);
    }

}
