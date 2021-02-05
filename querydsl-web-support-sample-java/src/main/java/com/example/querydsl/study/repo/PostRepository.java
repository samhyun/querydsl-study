package com.example.querydsl.study.repo;

import com.example.querydsl.study.entity.QPost;
import com.querydsl.core.types.dsl.DateTimePath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository implements QuerydslBinderCustomizer<QPost> {

    @Override
    public void customize(QuerydslBindings bindings, QPost post) {

        bindings.bind(post.createdAt)
                .all(((path, value) -> {
                    List<? extends Date> dates = new ArrayList<>(value);

                    if (dates.size() == 1) {
                        return Optional.of(post.createdAt.eq(dates.get(0)));
                    } else {
                        Date from = dates.get(0);
                        Date to = dates.get(1);
                        return Optional.of(post.createdAt.between(from, to));
                    }
                }));

        bindings.bind(Date.class).all((path, value) -> {
            List<? extends Date> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(((DateTimePath) path).eq(dates.get(0)));
            } else {
//                Date from = dates.get(0);
//                Date to = dates.get(1);
                return Optional.of(((DateTimePath) path).in(dates));
            }
        });
    }
}
