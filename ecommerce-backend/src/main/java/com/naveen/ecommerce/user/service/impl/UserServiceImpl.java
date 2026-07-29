//package com.naveen.ecommerce.user.service.impl;
//
//import com.naveen.ecommerce.common.exception.EmailAlreadyExistsException;
//import com.naveen.ecommerce.common.exception.PhoneNumberAlreadyExistsException;
//import com.naveen.ecommerce.common.exception.UsernameAlreadyExistsException;
//import com.naveen.ecommerce.auth.dto.request.RegisterRequest;
//import com.naveen.ecommerce.auth.dto.response.RegisterResponse;
//import com.naveen.ecommerce.user.entity.User;
//import com.naveen.ecommerce.user.enums.Role;
//import com.naveen.ecommerce.user.mapper.UserMapper;
//import com.naveen.ecommerce.user.repository.UserRepository;
//import com.naveen.ecommerce.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final UserMapper userMapper;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public RegisterResponse register(RegisterRequest request) {
//
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new EmailAlreadyExistsException("Email already exists");
//        }
//
//        if (userRepository.existsByUsername(request.getUsername())) {
//            throw new UsernameAlreadyExistsException("Username already exists");
//        }
//
//        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
//            throw new PhoneNumberAlreadyExistsException("Phone number already exists");
//        }
//
//        User user = userMapper.toUser(request);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.USER);
//
//
//        User savedUser = userRepository.save(user);
//
//        return userMapper.toRegisterResponse(savedUser);
//    }
//}