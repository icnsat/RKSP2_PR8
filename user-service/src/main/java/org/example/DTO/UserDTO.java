package org.example.DTO;

import lombok.Builder;
import lombok.Data;
import org.example.Model.Role;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}