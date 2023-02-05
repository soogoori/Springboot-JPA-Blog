package com.lookie.demo.service;

import com.lookie.demo.domain.RoleType;
import com.lookie.demo.domain.User;
import com.lookie.demo.repository.UserRepository;
import com.lookie.demo.web.dto.UserJoinRequestDto;
import com.lookie.demo.web.dto.UserResponseDto;
import com.lookie.demo.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    /**
     * 회원 조회
     */
    // 회원 전체 조회
    @Override
    public List<UserResponseDto> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // 회원 단건 조회
    @Override
    public UserResponseDto findUserById(Long userId){
        User entity = userRepository.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("회원이 존재하지 않습니다");
        });
        return new UserResponseDto(entity);
    }

    /**
     * 회원 등록
     */
    @Override
    @Transactional
    public Long join(UserJoinRequestDto requestDto){
        String rawPassword = requestDto.getPassword();
        System.out.println("rawPassword = " + rawPassword);
        System.out.println("Username = " +requestDto.getUsername());
        String encPassword = encoder.encode(rawPassword); //암호화
        requestDto.setPassword(encPassword);
        requestDto.setRole(RoleType.USER);
        return userRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 회원 삭제
     */
    @Override
    @Transactional
    public Long deleteUser(Long userId) {
        User entity = userRepository.findById(userId).orElseThrow(()->{
            throw new IllegalArgumentException("회원이 존재하지 않습니다");
        });
        userRepository.deleteById(userId);
        return entity.getId();
    }


    /**
     * 회원 정보 업데이트
     */
    @Override
    @Transactional
    public Long update(Long userId, UserUpdateRequestDto requestDto){
        User entity = userRepository.findById(userId).orElseThrow(()->{
            throw new IllegalArgumentException("회원이 존재하지 않습니다");
        });

        entity.update(requestDto.getPassword(),requestDto.getEmail());
        return userId;
    }
}
