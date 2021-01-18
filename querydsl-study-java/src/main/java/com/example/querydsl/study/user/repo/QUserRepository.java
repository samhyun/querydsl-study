package com.example.querydsl.study.user.repo;

import com.example.querydsl.study.user.dto.UserDto;

public interface QUserRepository {

    UserDto getById(long id);

}
