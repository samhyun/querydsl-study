package com.example.querydsl.study.repo;

import com.example.querydsl.study.entity.QPost;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import static com.example.querydsl.study.entity.QPost.post;

@Repository
public class PostRepository implements CustomQuerydslBinderCustomizer<QPost> {

    @Override
    public void addCustomization(QuerydslBindings bindings, QPost entityPath) {
        bindings.bind(post.content).first(StringExpression::contains);
    }
}
