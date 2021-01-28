package com.example.querydsl.study.comment.dto;


import com.example.querydsl.study.comment.entity.Comment;
import com.example.querydsl.study.core.dto.BaseDto;
import com.example.querydsl.study.user.dto.UserDto;
import com.example.querydsl.study.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommentDto extends BaseDto {

    private Long id;

    private String content;

    private UserDto writer;

    private long writerId;

    private long postId;

    private List<CommentDto> children;

    @QueryProjection
    public CommentDto(long id, String content, UserDto writer, Date createdAt, Date modifiedAt) {
        super(createdAt, modifiedAt);
        this.id = id;
        this.content = content;
        this.writer = writer;
    }

    @QueryProjection
    public CommentDto(long id, String content, UserDto writer, List<CommentDto> children, Date createdAt, Date modifiedAt) {
        this(id, content, writer, createdAt, modifiedAt);
        this.children = children;
    }
}
