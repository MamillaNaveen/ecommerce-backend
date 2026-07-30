package com.naveen.ecommerce.user.mapper;

import com.naveen.ecommerce.auth.dto.request.RegisterRequest;
import com.naveen.ecommerce.auth.dto.response.RegisterResponse;
import com.naveen.ecommerce.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T20:37:19+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterResponse toRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterResponse.RegisterResponseBuilder registerResponse = RegisterResponse.builder();

        registerResponse.id( user.getId() );
        registerResponse.username( user.getUsername() );
        registerResponse.email( user.getEmail() );
        registerResponse.phoneNumber( user.getPhoneNumber() );
        registerResponse.role( user.getRole() );

        return registerResponse.build();
    }

    @Override
    public User toUser(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.email( request.getEmail() );
        user.password( request.getPassword() );
        user.phoneNumber( request.getPhoneNumber() );

        return user.build();
    }
}
