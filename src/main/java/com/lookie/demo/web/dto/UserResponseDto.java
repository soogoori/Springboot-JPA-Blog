package com.lookie.demo.web.dto;

import com.lookie.demo.domain.RoleType;
import com.lookie.demo.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private String username;
    private String email;
    private RoleType role;
    private LocalDateTime createdDate;

    public UserResponseDto(User user){
        this.username=user.getUsername();
        this.email= user.getEmail();
        this.role = RoleType.USER;
        this.createdDate = user.getCreatedDate();
    }
}
