package com.weintern.library_management.controller;

import com.weintern.library_management.models.User;
import com.weintern.library_management.payload.AuthRequestDTO;
import com.weintern.library_management.payload.AuthResponseDTO;
import com.weintern.library_management.repository.UserRepository;
import com.weintern.library_management.security.CustomUserDetails;
import com.weintern.library_management.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(PasswordEncoder passwordEncoder, UserRepository userRepository,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setFullName(requestDTO.getFullName());
        user.setEmail(requestDTO.getEmail());
        user.setRole(requestDTO.getRole());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));

        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponseDTO(token, user.getUsername(), user.getRole().name()));
    }

}
