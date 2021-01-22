package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, QPostRepository {
}
