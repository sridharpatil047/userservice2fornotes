package org.example.userservice2.controllers;

import jakarta.validation.Valid;
import org.example.userservice2.dtos.*;
import org.example.userservice2.exceptions.TokenNotFoundException;
import org.example.userservice2.exceptions.UserAlreadyExistsException;
import org.example.userservice2.exceptions.UserNotExistsException;
import org.example.userservice2.models.Token;
import org.example.userservice2.models.User;
import org.example.userservice2.services.TokenService;
import org.example.userservice2.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    UserService userService;
    TokenService tokenService;

    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // Signup API
    @PostMapping("users/signup")
    public ResponseEntity<SignUpResponseDto> signup(
            @Valid @RequestBody SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException {

        User user = userService.registerUser(
                signUpRequestDto.getName(),
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword()
        );

        return ResponseEntity.ok(SignUpResponseDto.from(user));
    }


    // Login API
    @PostMapping("users/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws UserNotExistsException {

        Token token = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        return ResponseEntity.ok(LoginResponseDto.from(token));
    }


    // Logout API
    @PostMapping("users/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequestDto logoutRequestDto) throws TokenNotFoundException {

        tokenService.removeToken(logoutRequestDto.getToken());

        return ResponseEntity.ok("Logged out successfully");
    }

    // Validate Token API
    @PostMapping("tokens/validate")
    public ResponseEntity<ValidateResponseDto> validate(
            @RequestBody ValidateRequestDto validateRequestDto)
            throws TokenNotFoundException {

        User user = tokenService.validateToken(validateRequestDto.getToken());

        return ResponseEntity.ok(ValidateResponseDto.from(user));
    }
}
