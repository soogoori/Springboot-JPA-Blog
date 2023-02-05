package com.lookie.demo.service;

import com.lookie.demo.web.dto.BoardResponseDto;
import com.lookie.demo.web.dto.BoardSaveRequestDto;
import com.lookie.demo.web.dto.BoardUpdateRequestDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BoardService {

    // 게시물 저장
    Long save(BoardSaveRequestDto requestDto);
    Long save(String username, BoardSaveRequestDto requestDto);

    // 게시물 아이디로 게시물 찾기
    BoardResponseDto findById(Long id);

    // 전체 게시물 조회
    List<BoardResponseDto> findAll();

    // 게시물 제목 또는 내용 검색해서 조회
    List<BoardResponseDto> find(String title, String content);

    // 게시물 수정
    Long update(Long id, BoardUpdateRequestDto requestDto);

    // 게시물 삭제
    Long delete(Long id);
}
