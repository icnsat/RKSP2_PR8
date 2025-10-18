package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.AuthResponse;
import org.example.DTO.CreateUserDTO;
import org.example.DTO.LoginRequest;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody CreateUserDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestHeader("Authorization") String token) {
        return userService.validateToken(token);
    }
}

//package org.example.Controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.example.DTO.AuthResponse;
//import org.example.DTO.CreateUserDTO;
//import org.example.DTO.LoginRequest;
//import org.example.Service.AuthService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthService authService;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@Valid @RequestBody CreateUserDTO userDTO) {
//        AuthResponse response = authService.register(userDTO);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
//        AuthResponse response = authService.login(loginRequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/validate")
//    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            String jwtToken = token.substring(7);
//            boolean isValid = authService.validateToken(jwtToken);
//            return ResponseEntity.ok(isValid);
//        }
//        return ResponseEntity.ok(false);
//    }
//}