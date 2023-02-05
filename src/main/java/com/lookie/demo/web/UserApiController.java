package com.lookie.demo.web;

import com.lookie.demo.service.UserServiceImpl;
import com.lookie.demo.web.dto.UserJoinRequestDto;
import com.lookie.demo.web.dto.UserResponseDto;
import com.lookie.demo.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserServiceImpl userServiceImpl;

    /**
     * 회원 조회
     */
    // 전체 조회
    @GetMapping("/api/user")
    public List<UserResponseDto> userList(){
        return userServiceImpl.findAllUsers();
    }

    // 단건 조회
    @GetMapping("/api/user/{id}")
    public UserResponseDto userDetail(@PathVariable Long id){
        return userServiceImpl.findUserById(id);
    }

    /**
     * 회원 등록
     */
    @PostMapping("/auth/joinProc")
    public Long join(@RequestBody UserJoinRequestDto requestDto){
        return userServiceImpl.join(requestDto);
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/api/user/{id}")
    public Long delete(@PathVariable Long id){
        return userServiceImpl.deleteUser(id);
    }

    /**
     * 회원 업데이트
     */
    @PutMapping("/api/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return userServiceImpl.update(id, requestDto);
    }

}
