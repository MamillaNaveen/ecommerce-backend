package com.naveen.ecommerce.auth.service.impl;

import com.naveen.ecommerce.auth.dto.request.LoginRequest;
import com.naveen.ecommerce.auth.dto.request.RegisterRequest;
import com.naveen.ecommerce.auth.dto.response.LoginResponse;
import com.naveen.ecommerce.auth.dto.response.RegisterResponse;
import com.naveen.ecommerce.auth.service.AuthService;
import com.naveen.ecommerce.common.exception.EmailAlreadyExistsException;
import com.naveen.ecommerce.common.exception.PhoneNumberAlreadyExistsException;
import com.naveen.ecommerce.common.exception.UsernameAlreadyExistsException;
import com.naveen.ecommerce.security.jwt.JwtService;
import com.naveen.ecommerce.user.entity.User;
import com.naveen.ecommerce.user.enums.Role;
import com.naveen.ecommerce.user.mapper.UserMapper;
import com.naveen.ecommerce.user.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException("Phone number already exists");
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);


        User savedUser = userRepository.save(user);

        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }
}
