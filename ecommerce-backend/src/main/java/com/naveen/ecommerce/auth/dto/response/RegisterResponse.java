package com.naveen.ecommerce.auth.dto.response;

import com.naveen.ecommerce.user.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private Role role;
}
