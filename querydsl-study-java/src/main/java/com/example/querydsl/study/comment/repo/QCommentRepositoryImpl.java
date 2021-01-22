package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.core.querydsl.CustomQuerydslRepositorySupport;
import com.example.querydsl.study.post.entity.Post;
import com.example.querydsl.study.user.entity.User;
import com.querydsl.jpa.impl.JPAUpdateClause;

import java.util.Objects;

import static com.example.querydsl.study.comment.entity.QComment.comment;

public class QCommentRepositoryImpl extends CustomQuerydslRepositorySupport implements QCommentRepository {

    public QCommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public long update(CommentDto commentDto) {
        return new JPAUpdateClause(Objects.requireNonNull(getEntityManager()), comment)
                .set(comment.content, commentDto.getContent())
                .set(comment.writer, User.builder().id(commentDto.getWriterId()).build())
                .set(comment.post, Post.builder().id(commentDto.getPostId()).build())
                .where(comment.id.eq(commentDto.getId()))
                .execute();
    }
}
