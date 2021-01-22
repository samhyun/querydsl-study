package com.example.querydsl.study.comment.repo;

import com.example.querydsl.study.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QCommentRepository {
}
