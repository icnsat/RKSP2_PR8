package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.UserDTO;
import org.example.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}

//package org.example.Controller;
//
//import lombok.RequiredArgsConstructor;
//import org.example.DTO.UserDTO;
//import org.example.Service.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
//        UserDTO user = userService.getUserById(id);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<UserDTO> getCurrentUser(@RequestHeader("Authorization") String token) {
//        // Здесь можно извлечь username из токена и вернуть данные пользователя
//        // Упрощённая реализация
//        return ResponseEntity.ok().build();
//    }
//}