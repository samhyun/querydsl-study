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

    @Override
    default void addCustomization(QuerydslBindings bindings, QPost post) {
        bindings.bind(post.content).first(StringExpression::contains);
    }

    //
//    @Override
//    default void customize(QuerydslBindings bindings, QPost post) {
//        bindings.bind(post.createdAt).all((path, value) -> {
//            List<? extends Date> dates = new ArrayList<>(value);
//            if (dates.size() == 1) {
//                return Optional.of(path.eq(dates.get(0)));
//            } else {
//                Date from = dates.get(0);
//                Date to = dates.get(1);
//                return Optional.of(path.between(from, to));
//            }
//        });
//
//
//        bindings.bind(String.class).all((path, value) -> {
//            List<? extends String> values = new ArrayList<>(value);
//            if (values.size() == 1) {
//                String searchWord = values.get(0);
//                if (searchWord.startsWith("%")) {
//                    return Optional.of(((StringPath) path).like(searchWord));
//                } else {
//                    return Optional.of(((StringPath) path).like(searchWord));
//                }
//            } else {
//                return Optional.of(((StringPath) path).in(values));
//            }
//        });
//    }
}
