package com.lookie.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @Column(unique = true, nullable = false, length = 30)
    private String username; // 닉네임(id)

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role; // ADMIN, USER

    private Boolean enabled;

    // 업데이트
    public void update(String password, String email){
        this.password = password;
        this.email = email;
    }
}
