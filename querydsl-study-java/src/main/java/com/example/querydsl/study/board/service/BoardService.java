package com.example.querydsl.study.board.service;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.board.repo.BoardRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDto> findAll(Predicate predicate, Sort sort) {
        return this.boardRepository.findBoards(predicate, sort);
    }
}
