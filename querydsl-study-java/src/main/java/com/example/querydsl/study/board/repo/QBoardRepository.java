package com.example.querydsl.study.board.repo;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.board.entity.QBoard;
import com.example.querydsl.study.core.querydsl.CustomQuerydslBinderCustomizer;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface QBoardRepository extends CustomQuerydslBinderCustomizer<QBoard> {

    BoardDto getBoard(long boardId);

    List<BoardDto> findBoards(Predicate predicate, Sort sort);
}
