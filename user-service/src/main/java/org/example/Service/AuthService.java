//package org.example.Service;
//
//
//import lombok.RequiredArgsConstructor;
//import org.example.DTO.AuthResponse;
//import org.example.DTO.CreateUserDTO;
//import org.example.DTO.LoginRequest;
//import org.example.DTO.UserDTO;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;
//    private final UserService userService;
//
//    public AuthResponse login(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = jwtService.generateToken(userDetails);
//
//        UserDTO userDTO = userService.mapToDTO(
//                userService.getUserByUsername(loginRequest.getUsername())
//        );
//
//        return AuthResponse.builder()
//                .accessToken(token)
//                .tokenType("Bearer")
//                .user(userDTO)
//                .build();
//    }
//
//    public AuthResponse register(CreateUserDTO userDTO) {
//        UserDTO newUser = userService.createUser(userDTO);
//
//        UserDetails userDetails = userService.loadUserByUsername(newUser.getUsername());
//        String token = jwtService.generateToken(userDetails);
//
//        return AuthResponse.builder()
//                .accessToken(token)
//                .tokenType("Bearer")
//                .user(newUser)
//                .build();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            String username = jwtService.extractUsername(token);
//            UserDetails userDetails = userService.loadUserByUsername(username);
//            return jwtService.validateToken(token, userDetails);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}