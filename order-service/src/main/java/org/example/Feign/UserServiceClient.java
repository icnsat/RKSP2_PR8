package org.example.Feign;

import org.example.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/{userId}")
    UserDTO getUser(@PathVariable Long userId);

    @PostMapping("/api/auth/validate")
    boolean validateToken(@RequestHeader("Authorization") String token);
}
