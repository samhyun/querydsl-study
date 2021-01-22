package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.entity.Post;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QPostRepository {

    Page<PostDto> findPosts(Predicate predicate, Pageable pageable);

    PostDto getPost(long id);
}
