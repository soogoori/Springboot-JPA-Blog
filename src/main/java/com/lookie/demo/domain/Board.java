package com.lookie.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; //pk

    @Column(length = 500)
    private String title; //제목

    @Column(columnDefinition = "TEXT")
    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) // Board 기준으로
    @JoinColumn(name = "user_id") // 어떤 컬럼과 연결될 것인지
    private User user; // 글쓴이 -> 사용자

    @Builder //생성자
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}