package com.lookie.demo.service;

import com.lookie.demo.web.dto.UserJoinRequestDto;
import com.lookie.demo.web.dto.UserResponseDto;
import com.lookie.demo.web.dto.UserUpdateRequestDto;

import java.util.List;

public interface UserService {

    // 회원 조회
    List<UserResponseDto> findAllUsers();
    UserResponseDto findUserById(Long userId);

    //회원 등록
    Long join(UserJoinRequestDto requestDto);

    //회원 삭제
    Long deleteUser(Long userId);

    //회원 수정
    Long update(Long userId, UserUpdateRequestDto requestDto);

}
