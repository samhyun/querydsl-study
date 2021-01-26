package com.example.querydsl.study.comment.dto;


import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentDto {

    private long id;

    private String content;

    private UserDto writer;

    private long writerId;

    private long postId;

    private List<CommentDto> children;

    @QueryProjection
    public CommentDto(long id, String content, UserDto writer) {
        this.id = id;
        this.content = content;
        this.writer = writer;
    }

    @QueryProjection
    public CommentDto(long id, String content, UserDto writer, List<CommentDto> children) {
        this(id, content, writer);
        this.children = children;
    }
}
