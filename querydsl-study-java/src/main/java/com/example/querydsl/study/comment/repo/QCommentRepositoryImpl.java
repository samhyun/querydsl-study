package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.example.querydsl.study.comment.dto.QCommentDto;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.comment.entity.QComment;
import com.example.querydsl.study.core.querydsl.CustomQuerydslRepositorySupport;
import com.example.querydsl.study.user.dto.QUserDto;
import com.example.querydsl.study.user.entity.QUser;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.querydsl.study.comment.entity.QComment.comment;
import static com.example.querydsl.study.user.entity.QUser.user;

public class QCommentRepositoryImpl extends CustomQuerydslRepositorySupport implements QCommentRepository {

    public QCommentRepositoryImpl() {
        super(Comment.class);
    }

    private long count(Predicate predicate) {
        return from(comment).where(predicate).fetchCount();
    }

    @Override
    public Page<CommentDto> findComments(Predicate predicate, Pageable pageable) {
        QComment child = new QComment("child");
        QUser childWriter = new QUser("childWriter");
        return new PageImpl<>(from(comment).leftJoin(comment.children, child)
                .leftJoin(comment.writer, user)
                .leftJoin(child.writer, childWriter)
                .where(inCoveringIndex(predicate, pageable))
                .transform(
                        GroupBy.groupBy(comment.id)
                                .list(
                                        new QCommentDto(
                                                comment.id,
                                                comment.content,
                                                new QUserDto(comment.writer.id, comment.writer.nickname),
                                                GroupBy.list(
                                                        new QCommentDto(
                                                                child.id,
                                                                child.content,
                                                                new QUserDto(child.writer.id, child.writer.nickname).skipNulls(),
                                                                comment.createdAt,
                                                                comment.modifiedAt
                                                        ).skipNulls()
                                                ),
                                                comment.createdAt,
                                                comment.modifiedAt
                                        )
                                )
                ), pageable, this.count(predicate));
    }


    private BooleanExpression inCoveringIndex(Predicate predicate, Pageable pageable) {
        List<Long> ids = this.applyPagination(pageable, from(comment).select(comment.id).where(predicate))
                .fetch();
        return comment.id.in(ids);
    }
}
