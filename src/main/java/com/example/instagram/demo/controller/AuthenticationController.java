package com.example.instagram.demo.controller;


import com.example.instagram.demo.dto.ApiResponse;
import com.example.instagram.demo.dto.LoginDto;
import com.example.instagram.demo.dto.LoginResponse;
import com.example.instagram.demo.dto.RegisterUserDto;
import com.example.instagram.demo.model.User;
import com.example.instagram.demo.service.AuthenticationService;
import com.example.instagram.demo.service.JwtService;
import com.example.instagram.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginDto user) throws AuthenticationException {
        User authenticatedUser = authenticationService.authenticate(user);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(authenticatedUser.getUsername(), authenticatedUser.getProfilePicture(), jwtToken, jwtService.getExpirationTime());
        ApiResponse<LoginResponse> response = new ApiResponse<>("", loginResponse);
        return ResponseEntity.ok(response);
    }

}