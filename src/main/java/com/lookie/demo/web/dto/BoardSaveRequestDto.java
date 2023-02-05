package com.lookie.demo.web.dto;

import com.lookie.demo.domain.Board;
import com.lookie.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private User user;
    private LocalDateTime createdDate;


    @Builder
    public BoardSaveRequestDto(String title, String content, User user, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = createdDate;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}