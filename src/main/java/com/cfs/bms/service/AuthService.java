package com.cfs.bms.service;

import com.cfs.bms.dto.AuthResponseDto;
import com.cfs.bms.dto.LoginRequestDto;
import com.cfs.bms.dto.RegisterRequestDto;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.model.User;
import com.cfs.bms.repository.UserRepository;
import com.cfs.bms.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt hash
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole("USER");

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDto(token, user.getEmail(), user.getName());
    }

    public AuthResponseDto login(LoginRequestDto request) {
        // Throws BadCredentialsException if wrong email/password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDto(token, user.getEmail(), user.getName());
    }
}

