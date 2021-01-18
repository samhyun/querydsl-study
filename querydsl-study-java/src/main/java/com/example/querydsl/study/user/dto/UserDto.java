package com.example.querydsl.study.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private long id;

    private String name;

    @QueryProjection
    public UserDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
