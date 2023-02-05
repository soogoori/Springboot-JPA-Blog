package com.lookie.demo.web.dto;

import com.lookie.demo.domain.RoleType;
import com.lookie.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class UserJoinRequestDto {
    private String username;
    private String password;
    private String email;
    private RoleType role;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(RoleType.USER)
                .build();
    }
}
