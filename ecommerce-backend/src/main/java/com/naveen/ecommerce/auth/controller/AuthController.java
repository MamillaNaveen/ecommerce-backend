package com.naveen.ecommerce.auth.controller;

import com.naveen.ecommerce.auth.dto.request.LoginRequest;
import com.naveen.ecommerce.auth.dto.response.LoginResponse;
import com.naveen.ecommerce.auth.service.AuthService;
import com.naveen.ecommerce.common.response.ApiResponse;
import com.naveen.ecommerce.auth.dto.request.RegisterRequest;
import com.naveen.ecommerce.auth.dto.response.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = authService.register(request);

        ApiResponse<RegisterResponse> apiResponse =
                ApiResponse.<RegisterResponse>builder()
                        .success(true)
                        .message("User registered successfully")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        ApiResponse<LoginResponse> apiResponse =
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }


}