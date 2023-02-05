package com.lookie.demo.service;

import com.lookie.demo.domain.Board;
import com.lookie.demo.domain.User;
import com.lookie.demo.repository.BoardRepository;
import com.lookie.demo.repository.UserRepository;
import com.lookie.demo.web.dto.BoardResponseDto;
import com.lookie.demo.web.dto.BoardSaveRequestDto;
import com.lookie.demo.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 등록
    @Override
    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        requestDto.setUser(requestDto.getUser()); // user 정보 가져와서 post dto에 저장
        //requestDto.setUsername(requestDto.getUser().getUsername());
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long save(String username, BoardSaveRequestDto requestDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("해당 user가 없습니다. user=" + username));
        System.out.println(user.getUsername());
        requestDto.setUser(user); // user 정보 가져와서 post dto에 저장
        System.out.println(requestDto.getUser());
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    //조회
    @Override
    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new BoardResponseDto(entity);
    }

    @Override
    public List<BoardResponseDto> findAll(){
        return boardRepository.findAll()
                .stream()
                .map(board -> new BoardResponseDto(board))
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardResponseDto> find(String title, String content) {
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardRepository.findAll()
                    .stream()
                    .map(board -> new BoardResponseDto(board))
                    .collect(Collectors.toList());
        }else{
            return boardRepository.findByTitleOrContent(title, content)
                    .stream()
                    .map(board -> new BoardResponseDto(board))
                    .collect(Collectors.toList());
        }
    }

    //수정
    @Override
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        board.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    //삭제
    @Override
    @Transactional
    public Long delete(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id의 게시글입니다."));
        boardRepository.deleteById(id);
        return entity.getId();
    }


}