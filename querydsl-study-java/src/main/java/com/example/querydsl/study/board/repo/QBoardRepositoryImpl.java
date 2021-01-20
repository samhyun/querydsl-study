package com.example.querydsl.study.board.repo;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.dto.QBoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.board.entity.QBoard;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.querydsl.study.board.entity.QBoard.board;
import static com.example.querydsl.study.post.entity.QPost.post;

public class QBoardRepositoryImpl extends QuerydslRepositorySupport implements QBoardRepository {

    public QBoardRepositoryImpl() {
        super(QBoard.class);
    }

    @Override
    public BoardDto getBoard(long boardId) {
        return null;
    }

    @Override
    public List<BoardDto> findBoards(Predicate predicate) {
        return from(board)
                .select(
                        new QBoardDto(
                                board.id,
                                board.name,
                                board.type,
                                JPAExpressions.select(post.count()).from(post).where(post.board.id.eq(board.id))
                        )
                )
                .where(predicate)
                .fetch();
    }
}
