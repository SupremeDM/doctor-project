package com.moduletwo.innopolis.auth.controller;

import com.moduletwo.innopolis.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/get_auth_data")
    public ResponseEntity<Object> login() {

        return new ResponseEntity<>(authService.getUserInfo(),
                HttpStatus.OK);
    }
}
