package com.example.Sales.System.controller;

import com.example.Sales.System.dto.AuthenticationDTO;
import com.example.Sales.System.dto.AuthenticationResponseDTO;
import com.example.Sales.System.dto.UserDTO;
import com.example.Sales.System.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(authenticationDTO));
    }
}
