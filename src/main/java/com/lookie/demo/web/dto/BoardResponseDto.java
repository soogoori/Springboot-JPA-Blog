package com.lookie.demo.web.dto;

import com.lookie.demo.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    //private User user;
    private String username;
    private LocalDateTime createdDate;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        //this.user = entity.getUser();// -> 프록시를 가져와서 beanserializer 오류
        this.username = entity.getUser().getUsername();
        this.createdDate = entity.getCreatedDate();
    }
}
