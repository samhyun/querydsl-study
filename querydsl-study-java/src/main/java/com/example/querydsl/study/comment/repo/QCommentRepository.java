package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.dto.CommentDto;

public interface QCommentRepository {
    long update(CommentDto commentDto);
}
