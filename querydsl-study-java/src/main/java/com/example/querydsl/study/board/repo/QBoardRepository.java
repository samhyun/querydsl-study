package com.example.querydsl.study.board.repo;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface QBoardRepository {

    BoardDto getBoard(long boardId);

    List<BoardDto> findBoards(Predicate predicate);
}
