package com.example.querydsl.study.post.dto;

import com.example.querydsl.study.comment.dto.CommentDto;
import com.example.querydsl.study.user.dto.UserDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDto {

    private long id;

    private String title;

    private String content;

    private UserDto writer;

    private Date createdAt;

    private long commentCount;

    private List<CommentDto> comments;

    @QueryProjection
    public PostDto(long id, String title, UserDto writer, Date createdAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    @QueryProjection
    public PostDto(long id, String title, String content, UserDto writer, Date createdAt, long commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
    }

    @QueryProjection
    public PostDto(long id, String title, String content, UserDto writer, Date createdAt, long commentCount, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
        this.comments = comments;
    }
}
