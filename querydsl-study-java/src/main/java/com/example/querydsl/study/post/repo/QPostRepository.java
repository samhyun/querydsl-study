package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.core.querydsl.CustomQuerydslBinderCustomizer;
import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.entity.QPost;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface QPostRepository extends CustomQuerydslBinderCustomizer<QPost> {

    Page<PostDto> findPosts(Predicate predicate, Pageable pageable);

    PostDto getPost(long id);

    PostDto getPostWithComments(long id);
}
