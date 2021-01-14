package com.example.querydsl.study.repo;

import com.example.querydsl.study.dto.UserDto;

public interface QUserRepository {

    UserDto getById(long id);
}
