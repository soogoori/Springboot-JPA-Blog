package com.lookie.demo.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String password;
    private String email;
    private LocalDateTime modifiedDate;

    public UserUpdateRequestDto(String password, String email, LocalDateTime modifiedDate){
        this.password = password;
        this.email=email;
        this.modifiedDate = modifiedDate;
    }
}
