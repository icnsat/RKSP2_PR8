package org.example.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.Configuration.JwtUtil;
import org.example.DTO.AuthResponse;
import org.example.DTO.CreateUserDTO;
import org.example.DTO.LoginRequest;
import org.example.DTO.UserDTO;
import org.example.Model.Role;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(CreateUserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();

        User savedUser = userRepository.save(user);
        String token = JwtUtil.generateToken(user.getUsername());

        return AuthResponse.builder()
                .accessToken(token)
                .user(mapToDTO(savedUser))
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(user.getUsername());
        return AuthResponse.builder()
                .accessToken(token)
                .user(mapToDTO(user))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public UserDTO getUserById(Long id) {
        return mapToDTO(userRepository.findById(id).orElseThrow());
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}

//package org.example.Service;
//
//import lombok.RequiredArgsConstructor;
//import org.example.DTO.CreateUserDTO;
//import org.example.DTO.UserDTO;
//import org.example.Model.Role;
//import org.example.Model.User;
//import org.example.Repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserDTO createUser(CreateUserDTO userDTO) {
//        if (userRepository.existsByUsername(userDTO.getUsername())) {
//            throw new RuntimeException("Username already exists");
//        }
//        if (userRepository.existsByEmail(userDTO.getEmail())) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        User user = User.builder()
//                .username(userDTO.getUsername())
//                .email(userDTO.getEmail())
//                .password(passwordEncoder.encode(userDTO.getPassword()))
//                .role(Role.ROLE_CUSTOMER)
//                .build();
//
//        User savedUser = userRepository.save(user);
//        return mapToDTO(savedUser);
//    }
//
//    public UserDTO getUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        return mapToDTO(user);
//    }
//
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    public List<UserDTO> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(user.getRole().name())
//                .build();
//    }
//
//    public UserDTO mapToDTO(User user) {
//        return UserDTO.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .role(user.getRole())
//                .createdAt(user.getCreatedAt())
//                .build();
//    }
//}