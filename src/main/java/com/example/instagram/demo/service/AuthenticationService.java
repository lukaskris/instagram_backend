package com.example.instagram.demo.service;

import com.example.instagram.demo.dto.LoginDto;
import com.example.instagram.demo.dto.RegisterUserDto;
import com.example.instagram.demo.model.User;
import com.example.instagram.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(LoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User signup(RegisterUserDto input) {
        User user = new User()
                .setFullName(input.getFullName())
                .setUsername(input.getUsername())
                .setProfilePicture(input.getProfilePicture())
                .setRole(input.getRole())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }
}
