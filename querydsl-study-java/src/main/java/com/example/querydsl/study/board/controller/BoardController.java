package com.example.querydsl.study.board.controller;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.board.service.BoardService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;

    @GetMapping
    public List<BoardDto> findAllBoards(@QuerydslPredicate(root = Board.class) Predicate predicate, @SortDefault Sort sort) {
        return this.boardService.findAll(predicate, sort);
    }
}
