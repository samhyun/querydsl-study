package com.example.querydsl.study.post.service;

import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.repo.PostRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostDto> findAll(Predicate predicate, Pageable pageable) {
        return postRepository.findPosts(predicate, pageable);
    }
}
