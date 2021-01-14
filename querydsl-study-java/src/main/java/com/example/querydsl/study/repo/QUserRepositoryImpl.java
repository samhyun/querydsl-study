package com.example.querydsl.study.repo;

import com.example.querydsl.study.dto.QUserDto;
import com.example.querydsl.study.dto.UserDto;
import com.example.querydsl.study.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.example.querydsl.study.entity.QUser.user;

public class QUserRepositoryImpl extends QuerydslRepositorySupport implements QUserRepository {

    public QUserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public UserDto getById(long id) {
        return from(user).select(
                new QUserDto(user.id, user.firstName.append(" ").append(user.lastName))
        ).fetchFirst();
    }
}
