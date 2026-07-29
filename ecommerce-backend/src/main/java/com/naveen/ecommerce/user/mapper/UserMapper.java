package com.naveen.ecommerce.user.mapper;

import com.naveen.ecommerce.auth.dto.request.RegisterRequest;
import com.naveen.ecommerce.auth.dto.response.RegisterResponse;
import com.naveen.ecommerce.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponse toRegisterResponse(User user);

    User toUser(RegisterRequest request);

}