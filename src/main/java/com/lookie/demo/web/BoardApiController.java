package com.lookie.demo.web;

import com.lookie.demo.service.BoardServiceImpl;
import com.lookie.demo.web.dto.BoardResponseDto;
import com.lookie.demo.web.dto.BoardSaveRequestDto;
import com.lookie.demo.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardApiController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("/v1/board")
    public Long save(@RequestBody BoardSaveRequestDto requestDto) {
        return boardServiceImpl.save(requestDto);
    }

    //조회
    @GetMapping("/v1/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardServiceImpl.findById(id);
    }

    @GetMapping("/v1/boards")
    public List<BoardResponseDto> findAll() {
        List<BoardResponseDto> all = boardServiceImpl.findAll();
        return all;
    }

    // 제목 기준으로 조회
    @GetMapping("/v1/board")
    public List<BoardResponseDto> findByTitle(@RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String content){
        List<BoardResponseDto> boards = boardServiceImpl.find(title, content);
        return boards;
    }


    //수정
    @PutMapping("/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardServiceImpl.update(id, requestDto);
    }

    //삭제
    @DeleteMapping("/v1/board/{id}")
    public Long delete(@PathVariable Long id) {
        return boardServiceImpl.delete(id);
    }
}