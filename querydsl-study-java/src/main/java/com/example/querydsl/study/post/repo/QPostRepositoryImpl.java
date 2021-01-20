package com.example.querydsl.study.post.repo;

import com.example.querydsl.study.post.entity.QPost;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class QPostRepositoryImpl extends QuerydslRepositorySupport implements QPostRepository {

    public QPostRepositoryImpl() {
        super(QPost.class);
    }
}
