package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.post.dto.PostDto;
import com.example.querydsl.study.post.entity.QPost;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface QPostRepository extends QuerydslBinderCustomizer<QPost> {

    Page<PostDto> findPosts(Predicate predicate, Pageable pageable);

    PostDto getPost(long id);

    PostDto getPostWithComments(long id);


    @Override
    default void customize(QuerydslBindings bindings, QPost post) {
        bindings.bind(post.createdAt).all((path, value) -> {
            List<? extends Date> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(path.eq(dates.get(0)));
            } else {
                Date from = dates.get(0);
                Date to = dates.get(1);
                return Optional.of(path.between(from, to));
            }
        });
    }
}
