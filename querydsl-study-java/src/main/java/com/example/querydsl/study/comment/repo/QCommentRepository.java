package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QCommentRepository {

    Page<CommentDto> findComments(Predicate predicate, Pageable pageable);
}
