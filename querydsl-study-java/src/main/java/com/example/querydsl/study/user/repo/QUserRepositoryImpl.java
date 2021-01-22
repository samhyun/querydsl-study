package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.dto.QUserDto;
import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.example.querydsl.study.user.entity.QUser.user;

public class QUserRepositoryImpl extends QuerydslRepositorySupport implements QUserRepository {

    public QUserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public UserDto getById(long id) {
        return from(user).select(
                new QUserDto(user.id, user.email, user.nickname, user.lastName, user.firstName, user.mobile)
        ).where(user.id.eq(id)).fetchFirst();
    }
}
