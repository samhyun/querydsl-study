package com.example.querydsl.study.board.repo;

import com.example.querydsl.study.board.dto.BoardDto;
import com.example.querydsl.study.board.dto.QBoardDto;
import com.example.querydsl.study.board.entity.Board;
import com.example.querydsl.study.post.dto.QPostDto;
import com.example.querydsl.study.user.dto.QUserDto;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Map;

import static com.example.querydsl.study.board.entity.QBoard.board;
import static com.example.querydsl.study.post.entity.QPost.post;
import static com.example.querydsl.study.user.entity.QUser.user;

public class QBoardRepositoryImpl extends QuerydslRepositorySupport implements QBoardRepository {

    public QBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public BoardDto getBoard(long boardId) {
        Map<Long, BoardDto> result = from(board)
                .leftJoin(board.posts, post)
                .leftJoin(post.writer, user)
                .where(eqId(boardId))
                .transform(
                        GroupBy.groupBy(board.id)
                                .as(new QBoardDto(
                                        board.id,
                                        board.name,
                                        board.type,
                                        GroupBy.list(
                                                new QPostDto(
                                                        post.id,
                                                        post.title,
                                                        new QUserDto(user.id, user.nickname),
                                                        post.createdAt
                                                ).skipNulls()
                                        )
                                ))
                );

        return result.get(boardId);
    }

    @Override
    public List<BoardDto> findBoards(Predicate predicate, OrderSpecifier<?> orderBy) {
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
                .orderBy(orderBy)
                .fetch();
    }

    private BooleanExpression eqId(long boardId) {
        return board.id.eq(boardId);
    }

}
