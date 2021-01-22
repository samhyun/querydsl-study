package com.example.querydsl.study.comment.dto;


import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    private long id;

    private String content;

    private UserDto writer;

    private long writerId;

    private long postId;

    @QueryProjection
    public CommentDto(long id, String content, UserDto writer) {
        this.id = id;
        this.content = content;
        this.writer = writer;
    }

    public CommentDto(long id, String content, long writerId, long postId) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.postId = postId;
    }
}
