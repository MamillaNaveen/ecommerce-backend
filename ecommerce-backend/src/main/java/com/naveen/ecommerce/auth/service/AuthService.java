package com.naveen.ecommerce.auth.service;

import com.naveen.ecommerce.auth.dto.request.LoginRequest;
import com.naveen.ecommerce.auth.dto.request.RegisterRequest;
import com.naveen.ecommerce.auth.dto.response.LoginResponse;
import com.naveen.ecommerce.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
